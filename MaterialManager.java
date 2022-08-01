import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/** Manages loading all material types as well as their respective getters and setters */
public class MaterialManager {

    /** The list of all known material types */
    public ArrayList<Material> MATERIALS;

    /** Relative Path to the resource file containing the material types */
    private String filePath;



    /** Default constructor which will load resources from the default save file */
    public MaterialManager(String filePath) {

        // load default file path
        this.filePath = filePath;

        // load materials from file
        MATERIALS = new ArrayList<Material>();
    }



    /** Adds a new Material at it's assigned position as defined by the material's ID */
    public void addMaterial(Material material) {
        // Add new Material type at assigned ID
        MATERIALS.add(material.id, material);
        System.out.print("'" + material.name + "' has been added, Pictures {");
        for (String id : material.pictureID) {
            System.out.print(id + ", ");
        }
        System.out.print("}, id='" + material.id+"'");
        System.out.println();
    }



    /** Will parse a JSON file and add all contained material types to the ArrayList. Uses the default file. */
    public void loadMaterials() {
        loadMaterials(this.filePath);
    }



    /** Will parse a JSON file and add all contained material types to the ArrayList. Asks for a specific file to use. */
    public void loadMaterials(String filePath){

        // Try to read a .JSON file and return its parsed content as JSONObject
        JSONObject jsonObject = General.getJSONfromFile(filePath);

        // Iterate over Materials
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

            // Get array of material's pictureID(s)
            JSONArray pictureIDs = (JSONArray)currentMaterial.get("pictureIDs");

            // Iterate over PictureIDs & Add them
            for (Object picture : pictureIDs) {
                newMaterial.addPictureID((String) picture);
            }

            addMaterial(newMaterial);
        }
    }



    /** Will create a JSON file from the Material ArrayList and write it into a file. Uses the default file. */
    public void saveMaterials() {
        loadMaterials(this.filePath);
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

            /*
            // Add empty JSON Array inside each Material's JSON Object
            JSONArray singleMaterialArray = new JSONArray();

            // Add values id, name and isSolid to the Array
            singleMaterialArray.add("id : " + currentMaterial.id);
            singleMaterialArray.add("name : " + currentMaterial.name);
            singleMaterialArray.add("isSolid : " + currentMaterial.isSolid);
            */

            singleMaterial.put("id", currentMaterial.id);
            singleMaterial.put("name", currentMaterial.name);
            singleMaterial.put("isSolid", currentMaterial.isSolid);

            // Create a new JSON Array for PictureIDs
            JSONArray pictureIDs = new JSONArray();
            for (String picture : currentMaterial.pictureID)
                pictureIDs.add(picture);
            singleMaterial.put("pictureIDs", pictureIDs);

            // Insert Array for Material's Values into Material's Object
        //    singleMaterial.put("", singleMaterialArray);

            // Insert the current Material's Object into the Material's Array
            material.add(singleMaterial);
            json.put("material", material);
        }

        // Write JSON Object to file
        General.writeJSONtoFile(json, filePath);
    }
}
