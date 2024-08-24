package converter;

import org.json.JSONObject;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SerialPortReaderTest {

    @Test
    public void testReadDataFromInputStream() {
        // Simulierte Eingabedaten
        String testData = "{\"phonetype\":\"N95\",\"cat\":\"WP\"}\n";

        // Teste die Methode
        SerialPortReader reader = new SerialPortReader();
        JSONObject jsonObject = reader.parseLine(testData);
        // Überprüfe das Ergebnis
        assertEquals("N95", jsonObject.get("phonetype"));
        assertEquals("WP", jsonObject.get("cat"));
    }
}
