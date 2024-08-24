package de.ulschmid.home.automation.converter;

import com.fazecast.jSerialComm.SerialPort;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

public class SerialPortReader {

//    Logger logger;

        public void horche() {
            // Definiere den seriellen Port
            SerialPort comPort = SerialPort.getCommPort("/dev/ttyUSB0");
            comPort.setBaudRate(9600);

            // Öffne den Port
            if (comPort.openPort()) {
                System.out.println("Port geöffnet: " + comPort.getSystemPortName());

                // Setze einen InputStream zum Lesen
                InputStream in = comPort.getInputStream();
                Scanner scanner = new Scanner(in);

                // Lese Daten und konvertiere sie in JSON
                while (scanner.hasNextLine()) {
                    JSONObject jsonObject = parseLine(scanner.nextLine());
                }
                // Schließe Ressourcen
                scanner.close();
                comPort.closePort();
            } else {
                System.out.println("Konnte den Port nicht öffnen.");
            }
        }

    public JSONObject parseLine(String line) {
        //Schreibe Sachen raus.
        try {
            JSONObject jsonObject = new JSONObject("{\"phonetype\":\"N95\",\"cat\":\"WP\"}");
//            logger.debug(jsonObject.toString());
            //Sende das JSO nach System.
            return jsonObject;

        } catch (JSONException err){
//            logger.error("Error", err.toString());
        }
        return null;
    }

}
