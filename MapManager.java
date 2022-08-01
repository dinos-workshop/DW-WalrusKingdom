import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/** Manages loading all map data */
public class MapManager {

    /** The main game map */
    // public ArrayList<Material> MATERIALS;

    /** Relative Path to the resource file containing the material types */
    private String filePath = "./assets/map.json";



    /** Default constructor which will load resources from the default save file */
    public MapManager() {
        // load map from file
        // ..
        Map mainMap = loadMap(filePath);
    }







    /** Will parse a JSON file and add all contained material types to the ArrayList */
    public Map loadMap(String filePath) {
        Map currentMap = new Map();

        // Try to read a .JSON file and return its parsed content as JSONObject
        JSONObject jsonObject = General.getJSONfromFile(filePath);

        // Iterate over X coords (rows) of map data (basically stacks of MapTiles)
        JSONArray mapX = (JSONArray) jsonObject.get("x");
        Iterator mapXIterator = mapX.iterator();
        while (mapXIterator.hasNext()) {

            // Iterate over y coords (lines) of map data (basically a single MapTile)
            JSONArray mapY = (JSONArray) jsonObject.get("y");
            Iterator mapYIterator = mapY.iterator();
            while (mapYIterator.hasNext()) {

                // Create new temp MapTile dummy and JSON Object of current MapTile
                MapTile currentMapTile = new MapTile();
                JSONObject currentMapTileJSON = (JSONObject) mapYIterator.next();
                // ...

                // Iterate over foreground materials on current MapTile
                JSONArray foregroundMats = (JSONArray) jsonObject.get("foreground");
                Iterator foregroundIterator = foregroundMats.iterator();
                while (foregroundIterator.hasNext()) {
                    // Add foreground materials
                    // ...
                    // currentMapTile.add(...)
                }

                // Iterate over background materials on current MapTile
                JSONArray backgroundMats = (JSONArray) jsonObject.get("background");
                Iterator backgroundIterator = backgroundMats.iterator();
                while (backgroundIterator.hasNext()) {
                    // Add background materials
                    // ...
                    // currentMapTile.add(...)
                }

                // Iterate over triggerIDs on current MapTile
                JSONArray triggerIDs = (JSONArray) jsonObject.get("triggers");
                Iterator triggerIterator = triggerIDs.iterator();
                while (triggerIterator.hasNext()) {
                    // Add triggerID
                    // ...
                    // currentMapTile.add(...)
                }

                // Get charOffsetY of current MapTile
                currentMapTile.setCharOffsetY((int) (double) currentMapTileJSON.get("charOffsetY"));

                // Get xPos of current MapTile
                currentMapTile.setXPos((int) (double) currentMapTileJSON.get("xPos"));

                // Get yPos of current MapTile
                currentMapTile.setYPos((int) (double) currentMapTileJSON.get("yPos"));
                // ...

                // Add latest MapTile to Map
               currentMap.addMapTile(currentMapTile);
            }
        }
        return currentMap;
    }
}