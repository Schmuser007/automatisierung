package de.ulschmid.home.automation;

import org.eclipse.paho.client.mqttv3.*;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

public class MqttRs232Converter {

    private static final String MQTT_BROKER = "tcp://your-mqtt-broker:1883"; // Mosquito
    private static final String MQTT_TOPIC_SUBSCRIBE = "home/actuators/control";
    private static final String MQTT_TOPIC_PUBLISH = "home/sensors/data";
    private static final String SERIAL_PORT = "/dev/ttyS0";
    private static final int BAUD_RATE = 9600;

    private MqttClient mqttClient;
    private SerialPort serialPort;
    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    public static void main(String[] args) {
        MqttRs232Converter converter = new MqttRs232Converter();
        converter.initialize();
        converter.startMessageProcessing();
    }

    public void initialize() {
        try {
            connectToMqttBroker();
            connectToSerialPort();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void connectToMqttBroker() throws MqttException {
        mqttClient = new MqttClient(MQTT_BROKER, MqttClient.generateClientId());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);

        mqttClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                System.err.println("MQTT connection lost: " + cause.getMessage());
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) {
                String payload = new String(message.getPayload());
                messageQueue.add(payload);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // No action needed
            }
        });

        mqttClient.connect(options);
        mqttClient.subscribe(MQTT_TOPIC_SUBSCRIBE);
        //Gehen auch mehrere Topics.
//        mqttClient.subscribe(new String[]{MQTT_TOPIC_SUBSCRIBE, "Topic-B", "Topic-C"});
        System.out.println("Connected to MQTT Broker and subscribed to topic: " + MQTT_TOPIC_SUBSCRIBE);
    }

    private void connectToSerialPort() throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(SERIAL_PORT);

        if (portIdentifier.isCurrentlyOwned()) {
            throw new IOException("Serial port is currently in use");
        }

        CommPort commPort = portIdentifier.open(this.getClass().getName(), 2000);

        if (commPort instanceof SerialPort) {
            serialPort = (SerialPort) commPort;
            serialPort.setSerialPortParams(BAUD_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            System.out.println("Connected to serial port: " + SERIAL_PORT);
        } else {
            throw new IOException("Not a serial port");
        }
    }

    public void startMessageProcessing() {
        new Thread(() -> processMqttToSerial()).start();
        new Thread(() -> processSerialToMqtt()).start();
    }

    private void processMqttToSerial() {
        try (OutputStream outputStream = serialPort.getOutputStream()) {
            while (true) {
                String message = messageQueue.take();
                outputStream.write(message.getBytes());
                outputStream.flush();
                System.out.println("Sent to serial: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processSerialToMqtt() {
        try (InputStream inputStream = serialPort.getInputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                String receivedData = new String(buffer, 0, bytesRead);
                mqttClient.publish(MQTT_TOPIC_PUBLISH, new MqttMessage(receivedData.getBytes()));
                System.out.println("Published to MQTT: " + receivedData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

