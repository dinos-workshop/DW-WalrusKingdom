import java.awt.Component;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

@SuppressWarnings("serial")
public class TileSource extends Component {
    BufferedImage tileSource;


    /** Default Constructor, tries to import the tiles source file */
    public TileSource(String filePath) {
        int tileSize = General.getTileSize();
        try {
            tileSource = ImageIO.read(TileSource.class.getResourceAsStream(filePath));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (tileSource == null) {
            System.out.println("TILE SOURCE FILE NOT FOUND");
            return;
        }
    }


    /** Will calculate a tile's X-Position from its ID. Not the pixel position but the # of the correct tile row. */
    private static int getXPos(int tileID) {
        int tileX = (tileID%10);		// x TileSize
        return tileX;
    }

    /** Will calculate a tile's Y-Position from its ID. Not the pixel position but the # of the correct tile line. */
    private static int getYPos(int tileID) {
        int tileY = (tileID-(tileID%10))/10;
        return tileY;
    }



    /** Will cut a specific tile from the TileSource and return it. Used to initialize all the tiles. */
    public BufferedImage getTile(int tileID) {
        int tileSize = General.getTileSize();
        if (tileSource == null)
            return null;

        // Calculate the position of the tile inside the TileSource
        int tileX = getXPos(tileID);
        int tileY = getYPos(tileID);

        // Cut the actual tile from the TileSource and return it
        try {
            return tileSource.getSubimage(tileX*(tileSize+1)+1, tileY*(tileSize+1)+1, tileSize, tileSize);
        }
        catch(Exception e){
            System.out.println("Couldn't load tile subimage. If TILESIZE==" + tileSize + " is correct, better check the sourcefile.");
            return new BufferedImage(0, 0, 0);
        }
    }



    /** Will cut a specified pixel area from the TileSource and return it. */
    public BufferedImage getTileArea(int xPos, int yPos, int tileWidth, int tileHeight) {
        int tileSize = General.getTileSize();
        if (tileSource == null) {
            return null;
        }
        try {
            return tileSource.getSubimage(xPos, yPos, tileWidth-xPos, tileHeight-yPos);
        }
        catch(Exception e){
            System.out.println("Couldn't load tile subimage. If TILESIZE==" + tileSize + " is correct, better check the sourcefile.");
            return new BufferedImage(0, 0, 0);
        }
    }
}

