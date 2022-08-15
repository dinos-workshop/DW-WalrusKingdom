import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.ArrayList;

/** Manages loading all map data */
public class MapManager {

    /** The main game map */
    ArrayList<Map> MAPS = new ArrayList<Map>();

    /** Relative Path to the resource file containing the material types */
    private String filePath = "./assets/map.json";



    /** Default constructor which will load resources from the default save file */
    public MapManager() {
        // Load default File Path as no other was specified
        this.filePath = filePath;
    }



    /** Constructor which will load resources from a given save file */
    public MapManager(String filePath) {
        // Overwrite the default file path
        this.filePath = filePath;
    }



    /** Will parse a JSON file and return all contained MapTiles as a Map element. Uses the default file. */
    public void loadMaps() {
        // Load Maps from default File Path as no other was specified
        loadMaps(this.filePath);
    }



    /** Will parse a JSON file and return all contained MapTiles as a Map element */
    public void loadMaps(String filePath) {

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

            // Apply the Map's Size and FillMaterial which will fill map holes and be stacked in case the Map is smaller than the screen area

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

                // Apply MapTile's charOffsetY which is uset to simulate bridges and stairs (faking a 3rd dimension)
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
            // The map data has been put into a new Map object. Adding it to MapManager's Maps Array
            System.out.println(" Done");
            MAPS.add(currentMap);

            // Display the newly added map
            currentMap.showFirstLayerMap();
        }
    }



    /** Will create a JSON file from the Maps ArrayList and write it into a file. Uses the default file. */
    public void saveMaps() {
        // Calls overloaded method but with default file path as none was given
        saveMaps(this.filePath);
    }



    /** Will create a JSON file from the Maps ArrayList and write it into a file. Asks for a specific file to use. */
    public void saveMaps(String filePath) {

        // Create a new dummy JSONObject
        JSONObject json = new JSONObject();

        // Create JSONArray for all Maps
        JSONArray maps = new JSONArray();

        // Insert Material Data into JSON
        for (Map currentMap : MAPS ) {

            // Statistics on skipped MapTiles during Map Export
            int skippedMapTiles = 0;

            // Create JSON Objects for each map
            JSONObject currentMapObj = new JSONObject();

            // Apply Info Data to new JSON Object
            JSONObject MapInfoObj = new JSONObject();
            System.out.println("Map: "+currentMap.name);
            MapInfoObj.put("name", currentMap.name);
            MapInfoObj.put("spawnX", currentMap.spawnX);
            MapInfoObj.put("spawnY", currentMap.spawnY);
            MapInfoObj.put("spawnDir", currentMap.spawnDir);
            MapInfoObj.put("width", currentMap.width);
            MapInfoObj.put("height", currentMap.height);

            // TODO: Support Triggers inside FillMaterial

            // Create Objects for Filler Material
            JSONObject fillMaterialObj = new JSONObject();
            JSONArray foregroundFillMatArray = new JSONArray();
            JSONArray backgroundFillMatArray = new JSONArray();

            // Apply Filler Material
            for (Material material : currentMap.fillMaterial.foregroundMaterials)
                foregroundFillMatArray.add(material.id);
            for (Material material : currentMap.fillMaterial.backgroundMaterials)
                backgroundFillMatArray.add(material.id);

            // Create correct JSON Structure for Filler Material
            fillMaterialObj.put("foreground", foregroundFillMatArray);
            fillMaterialObj.put("background", backgroundFillMatArray);
            MapInfoObj.put("fillMaterial", fillMaterialObj);

            // Add Info Array to the Map's JSON Object
            currentMapObj.put("info", MapInfoObj);

            // Create Outer JSON Structure for Map Data (Array will contain Objects for each MapTile)
            JSONArray MapDataArray = new JSONArray();

            // Make sure the Map Data is not corrupted
            if ((currentMap.mapData.size() != currentMap.height) || (currentMap.mapData.get(0).size() != currentMap.width)) {
                System.out.println("ERROR: Size of Map does not match expectations. WRONG MAP DIMENSIONS");
            }

            // Iterate over separate Map Tiles
            for (int yPos = 0; yPos < currentMap.height; yPos++) {
                for (int xPos = 0; xPos < currentMap.width; xPos++) {

                    // Make sure the Map Data is not corrupted
                    if ((currentMap.mapData.get(yPos) == null) || (currentMap.mapData.get(yPos).size() == 0) || (currentMap.mapData.get(yPos).get(0) == null)) {
                        System.out.println("ERROR: Size of Map does not match expectations: MISSING MAP LINE");
                    }

                    // Get currently handled MapTile for easier access
                    MapTile currentMapTile = currentMap.mapData.get(yPos).get(xPos);

                    // Make sure the Map Data is not corrupted
                    if ((currentMapTile == null) || (currentMapTile.triggerIDs == null)) {
                        System.out.println("ERROR: Content of Map does not match expectations: MISSING MAP TILE DATA");
                    }

                    // Make sure the Tile is nur just an auto-generated Filler Material Tile
                    if (currentMapTile.getTriggerName(0).equals("NOT_A_TRIGGER_IS_FILLER_MATERIAL")) {
                        // System.out.println("Filler Tile, do not save");
                        skippedMapTiles++;
                    } else {
                        // Map Tile is valid, no corrupted Data, no auto-generated Filler Material. Save me!

                        // Create JSON Object for current Map Tile
                        JSONObject MapDataObj = new JSONObject();

                        // Create JSON Structure for Map Data
                        JSONArray foregroundArray = new JSONArray();
                        JSONArray backgroundArray = new JSONArray();
                        JSONArray triggerArray = new JSONArray();

                        // Apply Material IDs and Triggers
                        if (currentMapTile != null) {
                            for (Material foregroundMat : currentMapTile.foregroundMaterials)
                                foregroundArray.add(foregroundMat.id);

                            for (Material backgroundMat : currentMapTile.backgroundMaterials)
                                backgroundArray.add(backgroundMat.id);

                            // Make sure the MapTile has any triggers
                            if ((currentMapTile.triggerIDs.size() != 0) && (currentMapTile.triggerIDs.get(0) != null)) {
                                for (Trigger triggerNames : currentMapTile.triggerIDs) {
                                    triggerArray.add(triggerNames.name);
                                }
                            }
                        }

                        // Put freshly filled Data Arrays into correct JSON Structure
                        MapDataObj.put("foreground", foregroundArray);
                        MapDataObj.put("background", backgroundArray);
                        MapDataObj.put("triggers", triggerArray);

                        // TODO: Add Coords to Filler Material MapTiles so they don't all return as x=0,y=0
                        // TODO: Add a special trigger to the FillerMaterial so when applying new map tiles collisions with non-fillers can easily be detected

                        // Add additional data to current MapTile.
                        MapDataObj.put("x", xPos);
                        MapDataObj.put("y", yPos);
                        MapDataObj.put("charOffsetY", currentMapTile.charOffsetY);

                        // Add current MapTile's Data to the Array
                        MapDataArray.add(MapDataObj);
                    }
                }
                // Log progress (only for lines to reduce spam)
                System.out.print('.');
            }

            // Add Data Array to the Map's JSON Object
            currentMapObj.put("data", MapDataArray);

            // Add the finished map object to the Array
            maps.add(currentMapObj);

            // The Map's Data has been saved to the JSON File
            if (skippedMapTiles == 0) {
                System.out.println(" Done");
            } else {
                System.out.println(" Done (skipping " + skippedMapTiles + " of " + (currentMap.width* currentMap.height) + ')');
            }

        }

        // Write JSON Object to file
        json.put("Maps", maps);
        General.writeJSONtoFile(json, filePath);

        // All Map Data has been saved to the JSON File
        System.out.println("Done saving a total of " + MAPS.size() + " maps");
    }
}