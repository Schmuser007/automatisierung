package de.ulschmid.home.automation.converter;


public class SerialPortReaderTest {

    public void testReadDataFromInputStream() {
        // Simulierte Eingabedaten
        String testData = "{" +
                "    \"ReceiverID\":0,\n" +
                "    \"Voltage\":4.25,\n" +
                "    \"OriginID\":157, \n" +
                "    \"Status\":[false,true,false,true,false,true,false,false],\n" +
                "    \"StatusChanged\":[0,1,2,3,4,5,6,7]\n" +
                "}\n";

        // Teste die Methode
//        SerialPortReader reader = new SerialPortReader();
//        JSONObject jsonObject = reader.parseLine(testData);
        // Überprüfe das Ergebnis
//        try {
//            assertEquals(0, jsonObject.get("ReceiverID"));
//            assertEquals(4.25, jsonObject.get("Voltage"));
//            assertEquals(4.25, jsonObject.get("Voltage"));
//            assertEquals(157, jsonObject.get("Status"));
//            assertEquals(4.25, jsonObject.get("StatusChanged"));
//    } catch (JSONException e) {
//        throw new RuntimeException(e);
//    }
    }
}
