import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/** A collection of general purpose methods used by various other methods. Also contains the instances of all object managers. */
public class General {

    // The size of all tiles in pixels. Only to be changed if a 3rd party tile source is to be used
    public static int tileSize = 24;

    // The Trigger Manager's main instance */
    static TriggerManager triggerManager;

    /** The material manager's main instance */
    static MaterialManager materialManager;

    /** The map manager's main instance */
    static MapManager mapManager;



    /** Startup method which will initialise any required objects when the game launches */
    public static void Initialise() {

        // Initialise Trigger Manager so different Trigger Types can be handled
        System.out.println("Initialising Trigger Manager");
        triggerManager = new TriggerManager();

        // Initialise Material Manager so different Material Types can be handled
        System.out.println("Initialising Material Manager");
        materialManager = new MaterialManager("./assets/material.json");

        // Initialise Map Manager so map data can be handled
        System.out.println("Initialising Map Manager");
        mapManager = new MapManager("./assets/map.json");

        // Load Picture Data from a single huge PNG file, cutting it into hundreds of BufferedImage Objects
        System.out.println("Loading Image Assets");
        materialManager.loadTiles("./assets/tiles.png");

        // importOldData();

        // Load Material Data from JSON File into Material Manager
        System.out.println("Loading Material Data");
        materialManager.loadMaterials();

        // Load Map Data from JSON File into Map Manager
        System.out.println("Loading Map Data");
        mapManager.loadMaps();



        // Save the Map Data to a JSON File
        System.out.println("Saving Map Data");
        mapManager.saveMaps("./assets/map.json");


    }



    /** Tries to read a .JSON file and returns its parsed content as JSONObject */
    public static JSONObject getJSONfromFile(String filePath) {

        // Create new JSON Parser
        JSONParser parser = new JSONParser();

        // Try to Read and parse JSON File
        Object obj = null;
        try {
            obj = parser.parse(new FileReader(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
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



    /** Limits an integer value between a min and a max value */
    public static int getBetween(int _min, int _x, int _max) {
        int _ans = Math.max(_min, Math.min(_max, _x));
        return _ans;
    }



    /** Overwrites the game's tileSize, the size in pixels of each tile. Only to be changed if 3rd party Tiles are to be used */
    public static void setTileSize(int size) {
        tileSize = 24;
    }



    /** Returns the game's tileSize, the size in pixels of each tile. */
    public static int getTileSize() {
        return tileSize;
    }


    public static void importOldData() {
        // Regenerate old Material Data
        OldMaterialData.LoadOldMaterialData();

        // import old material data
        OldMaterialData.Import(materialManager);

        // Save all Material Data to the JSON file again, making sure only valid JSON Data is stored there
        System.out.println("Saving Material Data (Making sure JSON is valid)");
        materialManager.saveMaterials();

        // Regenerate old Map Data
        System.out.println("Loading old Map Data");
        OldMapData.loadCity1();
        OldMapData.Import(mapManager);
        OldMapData.loadForestHouse();
        OldMapData.Import(mapManager);
        OldMapData.loadMenu();
        OldMapData.Import(mapManager);
        OldMapData.loadIntro1();
        OldMapData.Import(mapManager);
    }
}
