import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

/** A collection of general purpose methods used by various other methods */
public class General {


    /** Tries to read a .JSON file and returns its parsed content as JSONObject */
    public static JSONObject getJSONfromFile(String filePath) {

        // Create new JSON Parser
        JSONParser parser = new JSONParser();

        // Try to Read and parse JSON File
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(filePath));
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (
                ParseException e) {
            e.printStackTrace();
        }

        // Return JSON Object
        return(JSONObject) obj;
    }
}
