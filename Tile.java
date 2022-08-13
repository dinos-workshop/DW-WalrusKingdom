import java.awt.image.BufferedImage;

/** A single Tile containing only an image and its assigned ID */
public class Tile {
    private BufferedImage image;
    public int ID;

    /** The default constructor, setting both image and ID */
    public Tile(BufferedImage image, int ID) {
        this.image = image;
        this.ID = ID;
    }



    /** Will return a tile's ID. Used to store map Data into files */
    public int getID() {
        return this.ID;
    }



    /** Overwrites a tile's image. */
    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
