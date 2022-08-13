import java.awt.image.BufferedImage;

// TODO: Comments
public class Tile {
    private BufferedImage image;
    public int ID;

    public Tile(BufferedImage image, int ID) {
        this.image = image;
        this.ID = ID;
    }



    public int getID() {
        return this.ID;
    }



    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
