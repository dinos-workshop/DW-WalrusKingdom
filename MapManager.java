import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;
import java.util.Iterator;

/** Manages loading all map data */
public class MapManager {

    /** The main game map */
    ArrayList<Map> MAPS = new ArrayList<Map>();

    /** Relative Path to the resource file containing the material types */
    private String filePath = "./assets/map.json";



    /** Default constructor which will load resources from the default save file */
    public MapManager() {

        // Load default File Path
        this.filePath = filePath;
    }



    /** Constructor which will load resources from a given save file */
    public MapManager(String filePath) {

        // overwrite the default file path
        this.filePath = filePath;
    }



    /** Will parse a JSON file and return all contained MapTiles as a Map element. Uses the default file. */
    public void loadMap() {
        loadMap(this.filePath);
    }



    /** Will parse a JSON file and return all contained MapTiles as a Map element */
    public void loadMap(String filePath) {

        // Try to read a .JSON file and return its parsed content as JSONObject
        JSONObject jsonObject = General.getJSONfromFile(filePath);

        // Iterate over separate Maps defined inside JSONObject
        JSONArray mapsJSON = (JSONArray) jsonObject.get("Maps");

        // Iterate over all Maps defined inside the JSON File
        for (Object mapObj : mapsJSON) {

            // Create a temp Map Dummy to use for each Map Object inside the File
            Map currentMap = new Map();

            // For each Map: Create JSON Object of current Map Object
            JSONObject mapJSON = (JSONObject) mapObj;

            // Get the current maps Info as a JSON Object
            Object mapInfoJSON = mapJSON.get("info");
            JSONObject mapInfoObj = (JSONObject) mapInfoJSON;

            // Apply the Map Name
            String mapName = (String) mapInfoObj.get("name");
            currentMap.setName(mapName);

            // Track Progress
            System.out.println("Initialising map '" + mapName + "' (1/2)");

            // Apply the Map Spawn Position
            currentMap.setSpawnX((int) (long) mapInfoObj.get("spawnX"));
            currentMap.setSpawnY((int) (long) mapInfoObj.get("spawnY"));

            // Apply the initial Spawn Direction
            currentMap.setSpawnDir((String) mapInfoObj.get("spawnDir"));

            // Apply the Map's Size and FillMaterial which will be stacked in case the Map is smaller than the screen area

            // Get the JSON Object for the FillMaterial Array
            Object fillMatObj = mapInfoObj.get("fillMaterial");
            JSONObject fillMatJSON = (JSONObject) fillMatObj;

            // Create a Dummy MapTile for the Filler Material
            MapTile fillMat = new MapTile();

            // Iterate over foreground materials of Filler Material
            JSONArray foregroundMats = (JSONArray) fillMatJSON.get("foreground");
            for (Object materialObj : foregroundMats) {

                // For all Foreground Materials: Add the current Material to the FillMaterial Dummy by handing over its ID
                fillMat.addForegroundMaterial((int) (long) materialObj);
            }

            // Iterate over background materials of Filler Material
            JSONArray backgroundMats = (JSONArray) fillMatJSON.get("background");
            for (Object materialObj : backgroundMats) {

                // For all Background Materials: Add the current Material to the FillMaterial Dummy by handing over its ID
                fillMat.addBackgroundMaterial((int) (long) materialObj);
            }

            // Apply the now created Fill Material together with the Map Size
            int height = (int) (long) mapInfoObj.get("height");
            int width = (int) (long) mapInfoObj.get("width");
            currentMap.setSize(width, height, fillMat);

            // Apply the actual Map Data

            // Get the current maps Data as a JSON Object, containing all the current Map's MapTiles
            JSONArray mapDataJSON = (JSONArray) mapJSON.get("data");

            // Track Progress
            System.out.println("Initialising " + width + 'x' + height + " map '" + mapName + " (2/2)");

            // Iterate over separate MapTiles defined inside JSONObject using a JSONObject
            for (Object currentMapTileObj : mapDataJSON) {
                JSONObject currentMapTileJSON = (JSONObject) currentMapTileObj;

                // Create Dummy MapTile Object
                MapTile currentMapTile = new MapTile();

                // Apply MapTile's X and Y values
                int xPos = (int) (long) currentMapTileJSON.get("x");
                int yPos = (int) (long) currentMapTileJSON.get("y");
                currentMapTile.setXPos(xPos);
                currentMapTile.setYPos(yPos);

                // Apply MapTile's charOffsetY
                currentMapTile.setCharOffsetY((int) (long) currentMapTileJSON.get("charOffsetY"));

                // Iterate over foreground materials on current MapTile in order to add them
                foregroundMats = (JSONArray) currentMapTileJSON.get("foreground");
                for (Object materialObj : foregroundMats) {

                    // For all Foreground Materials: Add the current Material to the current MapTile Dummy by handing over its ID
                    currentMapTile.addForegroundMaterial((int) (long) materialObj);
                }

                // Iterate over background materials on current MapTile in order to add them
                backgroundMats = (JSONArray) currentMapTileJSON.get("background");
                for (Object materialObj : backgroundMats) {

                    // For all Background Materials: Add the current Material to the current MapTile Dummy by handing over its ID
                    currentMapTile.addBackgroundMaterial((int) (long) materialObj);

                }

                // Iterate over triggerIDs on current MapTile in order to add them
                JSONArray triggerIDs = (JSONArray) currentMapTileJSON.get("triggers");
                for (Object triggerObj : triggerIDs) {

                    // For all TriggerIDs: Add the current Trigger to the current MapTile Dummy by handing over its Name
                    currentMapTile.addTrigger((String) triggerObj);
                }

                // Add latest MapTile to current Map
                currentMap.addMapTile(currentMapTile);

                // Track Progress
                System.out.print('.');

            }
            // The map data has been put into a new Map object. Adding it to Maps Array
            System.out.println(" Done");
            MAPS.add(currentMap);

            // Display the newly added map
            currentMap.showFirstLayerMap();
        }
    }
}