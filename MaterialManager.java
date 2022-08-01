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
    private String filePath = "./assets/material.json";



    /** Default constructor which will load resources from the default save file */
    public MaterialManager() {
        // load materials from file
        MATERIALS = new ArrayList<Material>();
        loadMaterials(filePath);
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



    /** Will parse a JSON file and add all contained material types to the ArrayList */
    public void loadMaterials(String filePath){

        // Try to read a .JSON file and return its parsed content as JSONObject
        JSONObject jsonObject = General.getJSONfromFile(filePath);

        // Iterate over Materials
        JSONArray Materials = (JSONArray) jsonObject.get("material");
        Iterator materialIterator = Materials.iterator();
        while (materialIterator.hasNext()) {

            // Get Material's JSON Object
            JSONObject currentMaterial = (JSONObject) materialIterator.next();

            // Get material's ID
            int id = (int) (double) currentMaterial.get("id");

            // Create new Material Instance
            Material newMaterial = new Material(id);

            // Get material's Name & Apply
            newMaterial.setName((String) currentMaterial.get("name"));

            // Get material's isSolid & Apply
            newMaterial.setIsSolid((boolean) currentMaterial.get("isSolid"));

            // Get array of material's pictureID(s)
            JSONArray pictureIDs = (JSONArray)currentMaterial.get("pictureIDs");

            // Iterate over PictureIDs & Add them
            Iterator picIterator = pictureIDs.iterator();
            while (picIterator.hasNext()) {
                newMaterial.addPictureID((String) picIterator.next().toString());
            }

            addMaterial(newMaterial);
        }
    }
}
