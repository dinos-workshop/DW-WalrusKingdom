import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/** Manages loading all material types as well as their respective getters and setters */
public class MaterialManager {

    /** The list of all known material types */
    public ArrayList<Material> MATERIALS;

    public ArrayList<Tile> TILES = new ArrayList<>(300);


    /** Relative Path to the resource file containing the material types */
    private String filePath;



    /** Default constructor which will load resources from the default save file */
    public MaterialManager(String filePath) {

        // load default file path
        this.filePath = filePath;

        // load materials from file
        MATERIALS = new ArrayList<Material>(1);
    }



    /** Adds a new Material at it's assigned position as defined by the material's ID */
    public void addMaterial(Material material) {

        // Should the Material Collection not have enough slots, add new ones
        if (MATERIALS.size()<=material.id) {
            for (int size = MATERIALS.size(); size <= material.id; size++) {
                MATERIALS.add(new Material(size));
            }
        }

        // Add new Material type at assigned ID
        MATERIALS.set(material.id, material);

        //System.out.print("'" + material.name + "' has been added, Pictures {");
        System.out.print('.');

        /*
        // Iterate over all included pictureIDs and print each of them
        for (String id : material.pictureID) {
            System.out.print(id + ", ");
        }
        System.out.print("}, id='" + material.id+"'");
        System.out.println();
         */
    }



    /** Will parse a JSON file and add all contained material types to the ArrayList. Uses the default file. */
    public void loadMaterials() {
        loadMaterials(this.filePath);
    }



    /** Will parse a JSON file and add all contained material types to the ArrayList. Asks for a specific file to use. */
    public void loadMaterials(String filePath){

        // Try to read a .JSON file and return its parsed content as JSONObject
        JSONObject jsonObject = General.getJSONfromFile(filePath);

        // Iterate over Materials in JSON Object
        JSONArray Materials = (JSONArray) jsonObject.get("material");
        for (Object material : Materials) {

            // Get JSONObject from current Material Object
            JSONObject currentMaterial = (JSONObject) material;

            // Get Material's ID
            long id = (long) currentMaterial.get("id");

            // Create new Material Instance
            Material newMaterial = new Material((int) id);

            // Get material's Name & Apply
            newMaterial.setName((String) currentMaterial.get("name"));

            // Get material's isSolid & Apply
            newMaterial.setIsSolid((boolean) currentMaterial.get("isSolid"));

            // Get array of material's tileID(s)
            // TODO: CHANGE TO TILE
            JSONArray tileIDs = (JSONArray) currentMaterial.get("pictureIDs");

            // Iterate over TileIDs & add them
            for (Object tile : tileIDs) {
                newMaterial.addTile(TILES.get(Integer.parseInt((String) tile)));
            }

            // Add the new Material to the Material Manager
            addMaterial(newMaterial);
        }

        // All Materials have been parsed and added to the Material Manager
        System.out.println(" Done");
    }



    /** Will create a JSON file from the Material ArrayList and write it into a file. Uses the default file. */
    public void saveMaterials() {

        // Calls overloaded method but with default file path as none was given
        saveMaterials(this.filePath);
    }



    /** Will create a JSON file from the Material ArrayList and write it into a file. Asks for a specific file to use. */
    public void saveMaterials(String filePath) {

        // Create a new dummy JSONObject
        JSONObject json = new JSONObject();

        // Create JSONArray for all Materials
        JSONArray material = new JSONArray();

        // Insert Material Data into JSON
        for (Material currentMaterial : MATERIALS ) {

            // Create JSON Objects for each material type
            JSONObject singleMaterial = new JSONObject();

            singleMaterial.put("id", currentMaterial.id);
            singleMaterial.put("name", currentMaterial.name);
            singleMaterial.put("isSolid", currentMaterial.isSolid);

            // Create a new JSON Array for Tiles
            JSONArray tilesArray = new JSONArray();
            for (Tile tile : currentMaterial.tiles)
                tilesArray.add(tile.getID());
            singleMaterial.put("tiles", tilesArray);

            // Insert Array for Material's Values into Material's Object
            // singleMaterial.put("", singleMaterialArray);

            // Insert the current Material's Object into the Material's Array
            material.add(singleMaterial);
            json.put("material", material);

            System.out.print('.');
        }

        // Write JSON Object to file
        General.writeJSONtoFile(json, filePath);

        // All Material Data has been saved to the JSON File
        System.out.println(" Done");
    }



    /** Returns a single Material's Image by its ID */
    public Tile getTileByID(int tileID) {
        // TODO: sanity checks
        return TILES.get(tileID);
    }



    /** Imports all the Material's Images from a single PNG file */
    public void loadPictures(String filePath) {
        // TODO: IMPLEMENT ME PLZ
        TILES = new ArrayList<Tile>(300);

        for (int i = 1; i<300; i++) {
            Tile tile = new Tile();
            tile.ID = i;
            while (TILES.size() <= i) {
                TILES.add(tile);
            }
            TILES.set(i, tile);
        }
    }
}
