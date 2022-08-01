import java.util.ArrayList;

/** A single map element made up of multiple background and foreground materials which also contains any game engine data for that position
 * foregroundMaterials: ArrayList of all Materials rendered in front of characters
 * backgroundMaterials: ArrayList of all Materials rendered behind characters
 * triggerIDs: ArrayList of all trigger regions present in the area, such as silent teleports, speed effects or interactions
 * charOffsetY: Value by which characters at this position will be shifted up/down, to be used for stairs and bridges to imply height levels
 */
public class MapTile {
    ArrayList<Material> foregroundMaterials;
    ArrayList<Material> backgroundMaterials;
    ArrayList<Trigger> triggerIDs;
    int charOffsetY;
    int xPos;
    int yPos;

    /** Default (empty) constructor used if all parameters are to be added later */
    public MapTile() { }

    /** Sets the value by which characters at this position will be shifted up/down, to be used for stairs and bridges to imply height levels */
    public void setCharOffsetY(int offset) {
        this.charOffsetY = offset;
    }

    // TODO: Javadoc Comments
    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
}
