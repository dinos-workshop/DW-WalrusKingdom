import java.util.ArrayList;

/**
 * Defines material types which represent building blocks for the map. Multiple Materials may be combined to create multi-layer Maps.
 * id: Material-ID, used in map data to reference a material
 * name: The name of a material, mainly used for debugging
 * isSolid: To check if walking through this material is possible
 * pictureID: picture link (maybe later replace this with a bitmap object or something) */
public class Material {
    int id;
    String name;
    boolean isSolid;
    ArrayList<String> pictureID;

    /** Standard constructor */
    public Material(int id, String name, boolean isSolid, ArrayList<String> pictureID) {
        this.id = id;                   // the id which represents the position in the array
        this.name = name;				// just for fun
        this.isSolid = isSolid;		    // to check if walking through this is possible
        this.pictureID = pictureID;	    // picture link (maybe later replace this with a bitmap object or something)
        // So I can check for these IDs instead of having to check for hard-coded coordinates
        // Only for events that are always triggered by such a block, live Saving-points.
    }

    /** Simplified Constructor used if other parameters are to be added later */
    public Material(int id){
        this.id = id;
        this.pictureID = new ArrayList<String>();
    }

    /** Overwrite the Name parameter */
    public void setName(String name) {
        this.name = name;
    }

    /** Overwrite the isSolid parameter */
    public void setIsSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    /** Overwrite the PictureID Parameter with a new ArrayList containing only the new enty */
    public void setPictureID(String id) {
        this.pictureID = new ArrayList<String>();
        pictureID.add(id);
    }

    /** Add another PictureID to the Material */
    public void addPictureID(String id) {
        this.pictureID.add(id);
    }

}

// TODO: Replace String PictureID with actual bitmaps