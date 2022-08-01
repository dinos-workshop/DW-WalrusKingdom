import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** A collection of general purpose methods used by various other methods */
public class General {
    static MaterialManager materialManager;


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



    /** Tries to write JSON-Objects into files */
    public static void writeJSONtoFile(JSONObject json, String filePath) {

        // Try to create new file at given path and write JSON content
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(json.toJSONString());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("JSON file created: " + json);
    }



    /** Returns a material from the Material Collection by its MaterialID */
    public static Material materialFromID(int materialID) {
        return materialManager.MATERIALS.get(materialID);
    }



    /** Startup method which will initialise any required objects when the game launches */
    public static void Initialise() {

        // Initialise Material Manager so different Material Types can be handled
        System.out.println("Initialising Material Manager");
        materialManager = new MaterialManager("./assets/material.json");

        System.out.println("Loading Material Data");
        materialManager.loadMaterials();

        System.out.println("Saving Material Data");
        materialManager.saveMaterials("./assets/material_OUT.json");

        System.out.println("Done");
        // Initialise Map Manager so map data can be handled
        // TODO: Implement map manager
    }
}
