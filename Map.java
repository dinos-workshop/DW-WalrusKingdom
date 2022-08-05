import java.util.ArrayList;

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
        // TODO: Check for collisions

        // Add empty ArrayList to adapt map's first dimension if necessary
        while (this.mapData.size() <= newMapTile.yPos) {
            this.mapData.add(new ArrayList<MapTile>());
        }

        // Add empty Fields to adapt map's second dimension if necessary
        while (this.mapData.get(newMapTile.yPos).size() <= newMapTile.xPos) {
            this.mapData.get(newMapTile.yPos).add(null);

        }

        System.out.println("size="+this.mapData.size()+"x" + this.mapData.get(newMapTile.yPos).size() + " pos="+newMapTile.yPos+","+newMapTile.xPos);
        this.mapData.get(newMapTile.yPos).add(newMapTile.xPos, newMapTile);
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
    public void setSize(int width, int height) {
        this.height = height;
        this.width = width;
        while (this.mapData.size() < this.height) {
            this.mapData.add(new ArrayList<MapTile>(this.width));
            System.out.println("size="+this.mapData.size()+"x" + this.mapData.get(0).size() + " set to "+this.height+"x"+this.width);
        }
    }

    /** Set the Filling Material which will be stretched around the edges should the map be smaller than the screen area */
    public void setFillMaterial(MapTile fillMaterial) {
        this.fillMaterial = fillMaterial;
    }
}

// TODO: after loading all the map data, iterate over all coordinates and layers in order to create a 2d-bool-array of isSolid (loading collision data)

