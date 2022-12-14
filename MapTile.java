import java.util.ArrayList;

/** A single map element made up of multiple background and foreground materials which also contains any game engine data for that position
 * foregroundMaterials: ArrayList of all Materials rendered in front of characters
 * backgroundMaterials: ArrayList of all Materials rendered behind characters
 * triggerIDs: ArrayList of all trigger regions present in the area, such as silent teleports, speed effects or interactions
 * charOffsetY: Value by which characters at this position will be shifted up/down, to be used for stairs and bridges to imply height levels
 * xPos: The x-Position of the tile
 * yPos: The y-Position of the tile
 */
public class MapTile {
    ArrayList<Material> foregroundMaterials;
    ArrayList<Material> backgroundMaterials;
    ArrayList<Trigger> triggerIDs;
    int charOffsetY;
    int xPos;
    int yPos;



    /** Default (empty) constructor used if all parameters are to be added later */
    public MapTile() {
        this.foregroundMaterials = new ArrayList<Material>(1);
        this.backgroundMaterials = new ArrayList<Material>(1);
        this.triggerIDs = new ArrayList<Trigger>(1);
    }



    /** Sets the value by which characters at this position will be shifted up/down, to be used for stairs and bridges to imply height levels */
    public void setCharOffsetY(int offset) {
        this.charOffsetY = offset;
    }



    /** Sets the x-position for a MapTile */
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }



    /** Sets the y-position for a MapTile */
    public void setYPos(int yPos) {
        this.yPos = yPos;
    }



    /** Adds a Material to the Foreground Materials */
    public void addForegroundMaterial(Material material) {
        this.foregroundMaterials.add(material);
    }



    /** Adds a Material to the Foreground Materials by its MaterialID */
    public void addForegroundMaterial(int materialID) {
        Material material = General.materialFromID(materialID);

        // Sanity Check
        if (material != null) {
            this.foregroundMaterials.add(material);
        } else {
            System.out.println("ERROR: Material id='" + materialID + "' has returned NULL during lookup. Please check material.json");
        }
    }


    /** Adds a Material to the Background Materials */
    public void addBackgroundMaterial(Material material) {
        this.backgroundMaterials.add(material);
    }



    /** Adds a Material to the Background Materials by its MaterialID */
    public void addBackgroundMaterial(int materialID) {
        Material material = General.materialFromID(materialID);
        if (material != null) {
            this.backgroundMaterials.add(material);
        } else {
            System.out.println("ERROR: Material id='" + materialID + "' has returned NULL during lookup. Please check material.json");
        }
    }



    /** Adds a Trigger to the MapTile */
    public void addTrigger(Trigger trigger) {
        this.triggerIDs.add(trigger);
    }



    /** Adds a Trigger to the MapTile by its Name */
    public void addTrigger(String trigger) {

        // Get the Trigger object from MaterialManager and add it to the ArrayList of Triggers
        this.addTrigger(General.triggerManager.getTriggerByName(trigger));
    }



    // Returns a Trigger's Name by its Array Position or an empty String if there is no such Trigger
    public String getTriggerName(int position) {
        // Make sure the Trigger is valid and return its Name
        if(this.triggerIDs.size()>position && triggerIDs.get(position)!=null) {
            return this.triggerIDs.get(position).name;
        } else {
            // Return an empty String to avoid Null Pointer Exceptions
            return "";
        }
    }





}

