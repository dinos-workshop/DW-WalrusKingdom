import java.util.ArrayList;
import java.util.Arrays;

/** A complete Map made up of a 2D-Array of separate MapTiles. It also contains map-specific information.
 * mapData: The actual map data
 * name: The Map's Name
 * spawnX: The spawn location's X-coordinate
 * spawnY: The spawn location's Y-coordinate
 */
public class Map {
    String name;
    int spawnX;
    int spawnY;
    int width;
    int height;
    String spawnDir;
    MapTile fillMaterial; // stacked outside the map should the map be smaller than the screen area
    ArrayList <ArrayList <MapTile> > mapData = new ArrayList <ArrayList <MapTile> >();



    /** The default constructor, only creating an empty first row and column for the 2D-Map */
    public Map() {

        // Adds first Row
        // this.mapData.add(new ArrayList<MapTile>());
    }



    /** Adds a new MapTile at it's assigned position as defined by xPos and yPos */
    public void addMapTile(MapTile newMapTile) {

        System.out.println("id: " + newMapTile.foregroundMaterials.get(0).id);

        // Limit both xPos and yPos to the Map size
        int yPos = Math.min(this.mapData.size() - 1, newMapTile.yPos);
        int xPos = Math.min(this.mapData.get(yPos).size() - 1, newMapTile.xPos);

        // Check if the requested position is actually outside the Map
        if ((yPos != newMapTile.yPos) || (xPos != newMapTile.xPos))
            System.out.println("WARN: Out of bounds map tile coordinate: (x" + newMapTile.xPos + "/y" + newMapTile.yPos);

        // Add the MapTile at the adapted coordinates, overwriting the default FillerMaterial
        this.mapData.get(yPos).set(xPos, newMapTile);
    }



    /** Set the Name of the Map */
    public void setName(String name) {
        this.name = name;
    }



    /** Set the X-Coordinate for any new character's spawn point on the map */
    public void setSpawnX(int spawnX) {
        this.spawnX = spawnX;
    }



    /** Set the Y-Coordinate for any new character's spawn point on the map */
    public void setSpawnY(int spawnY) {
        this.spawnY = spawnY;
    }



    /** Set the direction which a newly spawned character will face */
    public void setSpawnDir(String spawnDir) {
        this.spawnDir = spawnDir;
    }



    /** Apply the given map size to the 2D ArrayList */
    public void setSize(int width, int height, MapTile fillMaterial) {

        // Apply new values
        this.height = height;
        this.width = width;
        this.fillMaterial = fillMaterial;

        // Set reserved Space for Map ArrayList
        while (this.mapData.size() < this.height) {
            this.mapData.add(new ArrayList<MapTile>(this.width));
            // System.out.println("size="+this.mapData.size()+"x" + this.mapData.get(0).size() + " set to "+this.height+"x"+this.width);
        }

        // Iterate over 2D ArrayList and fill the ArrayList with the intended fillerMaterial
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                // Add FillerMaterial which will be overwritten with actual MapData
                this.mapData.get(y).add(this.fillMaterial);
            }

            // Track progress
            System.out.print('.');
        }

        // The ArrayList has been set to the intended size, job well done
        System.out.println(" Done");
    }


    /** Will print the first Foreground Material's ID of each MapTile to give a general Idea about a map */
    public void showFirstLayerMap() {

        // Iterate over Y-Coordinate of Map Data
        for (ArrayList<MapTile> y : mapData) {

            // Iterate over X-Coordinate of Map Data
            for (MapTile x : y) {

                // Print the first of the Foreground-Material's IDs
                System.out.print(x.foregroundMaterials.get(0).id + "   ");
            }

            // Add a line break
            System.out.println("");
        }
    }



    /** Set the Filling Material which will be stretched around the edges should the map be smaller than the screen area */
    public void setFillMaterial(MapTile fillMaterial) {
        this.fillMaterial = fillMaterial;
    }
}

// TODO: after loading all the map data, iterate over all coordinates and layers in order to create a 2d-bool-array of isSolid (loading collision data)

