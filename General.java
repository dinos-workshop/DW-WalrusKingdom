import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** A collection of general purpose methods used by various other methods */
public class General {

    // The Trigger Manager's main instance */
    static TriggerManager triggerManager;

    /** The material manager's main instance */
    static MaterialManager materialManager;

    /** The map manager's main instance */
    static MapManager mapManager;

    // TODO: Rethink decision to put the main instances here



    /** Startup method which will initialise any required objects when the game launches */
    public static void Initialise() {

        // Initialise Trigger Manager so different Trigger Types can be handled
        System.out.println("Initialising Trigger Manager");
        triggerManager = new TriggerManager();

        // Initialise Material Manager so different Material Types can be handled
        System.out.println("Initialising Material Manager");
        materialManager = new MaterialManager("./assets/material.json");

        // Load Material Data from JSON File into Material Manager
         System.out.println("Loading Material Data");
         materialManager.loadMaterials();

        // regenerate old data
        // OldMaterialData.LoadOldMaterialData();

        // import old data
        // OldMaterialData.Import(materialManager);

        // Save all Material Data to the JSON file again, making sure only valid JSON Data is stored there
        System.out.println("NOT Saving Material Data (Making sure JSON is valid) AS IT BREAKS STUFF");
        // materialManager.saveMaterials();

        // Initialise Map Manager so map data can be handled
        System.out.println("Initialising Map Manager");
        mapManager = new MapManager("./assets/map.json");
        mapManager.loadMap();
        // TODO: Implement map manager
    }



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

            // Create a new FileWriter for the given File Path
            FileWriter file = new FileWriter(filePath);

            // Parse the JSON Data to a String and write it
            file.write(json.toJSONString());

            // Close the fileWriter again
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println("JSON file created: " + json);
    }



    /** Returns a material from the Material Collection by its MaterialID */
    public static Material materialFromID(int materialID) {
        return materialManager.MATERIALS.get(materialID);
    }





}
