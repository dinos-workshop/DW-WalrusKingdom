import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

/** Manages loading all map data */
public class MapManager {

    /** The main game map */
    Map mainMap;

    /** Relative Path to the resource file containing the material types */
    private String filePath = "./assets/map.json";



    /** Default constructor which will load resources from the default save file */
    public MapManager() {

        // Load default File Path
        this.filePath = filePath;

    }







    /** Will parse a JSON file and return all contained MapTiles as a Map element */
    public Map loadMap(String filePath) {

        // Create dummy Map object
        Map currentMap = new Map();

        // Try to read a .JSON file and return its parsed content as JSONObject
        JSONObject jsonObject = General.getJSONfromFile(filePath);

        // Iterate over MapTiles inside JSONObject
        JSONArray MapTiles = (JSONArray) jsonObject.get("MapTile");
        for (Object mapTileObj : MapTiles) {

            // For each MapTile: Create JSON Object of current JSON MapTile and new temp MapTile dummy
            JSONObject mapTileJSON = (JSONObject) mapTileObj;
            MapTile currentMapTile = new MapTile();

            // Apply MapTile's X and Y values
            currentMapTile.setXPos((int) mapTileJSON.get("x"));
            currentMapTile.setYPos((int) mapTileJSON.get("y"));

            // Apply MapTile's charOffsetY
            currentMapTile.setCharOffsetY((int) mapTileJSON.get("charOffsetY"));

            // Iterate over foreground materials on current MapTile
            JSONArray foregroundMats = (JSONArray) mapTileJSON.get("foreground");
            for (Object materialObj : foregroundMats) {

                // For all Foreground Materials: Create JSON Object of the current Material
                JSONObject materialJSON = (JSONObject) materialObj;

                // Add the current Material to the current MapTile Dummy by handing over its ID
                currentMapTile.addForegroundMaterial((int) materialJSON.get("id"));

            }

            // Iterate over background materials on current MapTile
            JSONArray backgroundMats = (JSONArray) mapTileJSON.get("background");
            for (Object materialObj : backgroundMats) {

                // For all Background Materials: Create JSON Object of the current Material
                JSONObject materialJSON = (JSONObject) materialObj;

                // Add the current Material to the current MapTile Dummy by handing over its ID
                currentMapTile.addBackgroundMaterial((int) materialJSON.get("id"));

            }

            // Iterate over triggerIDs on current MapTile
            JSONArray triggerIDs = (JSONArray) mapTileJSON.get("triggers");
            for (Object triggerObj : triggerIDs) {

                // For all TriggerIDs: Create JSON Object of the current Trigger
                JSONObject triggerJSON = (JSONObject) triggerObj;

                // Add the current Trigger to the current MapTile Dummy by handing over its ID
                currentMapTile.addTrigger((String) triggerJSON.get("Name"));

            }
            Iterator triggerIterator = triggerIDs.iterator();
            while (triggerIterator.hasNext()) {
                // Add triggerID
                // ...
                // currentMapTile.add(...)
            }



            // Add latest MapTile to Map
           currentMap.addMapTile(currentMapTile);
        }
        return currentMap;
    }
}