import java.awt.image.BufferedImage;
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
    ArrayList<Tile> tiles;

    /** Standard constructor */
    public Material(int id, String name, boolean isSolid, ArrayList<Tile> tiles) {
        this.id = id;                   // the id which represents the position in the array
        this.name = name;				// just for fun
        this.isSolid = isSolid;		    // to check if walking through this is possible
        this.tiles = tiles;	            // tile object containing a picture
        // So I can check for these IDs instead of having to check for hard-coded coordinates
        // Only for events that are always triggered by such a block, live Saving-points.
    }

    /** Simplified Constructor used if other parameters are to be added later */
    public Material(int id){
        this.id = id;
        this.tiles = new ArrayList<Tile>(1);
    }

    /** Overwrite the Name parameter */
    public void setName(String name) {
        this.name = name;
    }

    /** Overwrite the isSolid parameter */
    public void setIsSolid(boolean isSolid) {
        this.isSolid = isSolid;
    }

    /** Overwrite the PictureID Parameter with a new ArrayList containing only the new entry */
    public void setTile(Tile tile) {
        this.tiles = new ArrayList<Tile>();
        tiles.add(tile);
    }

    /** Add another PictureID to the Material */
    public void addTile(Tile tile) {
        this.tiles.add(tile);
    }

}

// TODO: Replace String PictureID with actual bitmaps