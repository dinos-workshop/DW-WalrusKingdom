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
    ArrayList <ArrayList <MapTile> > mapData;

    // TODO: after loading all the map data, iterate over all coordinates and layers in order to create a 2d-bool-array of isSolid (loading collision data)

    // TODO: Javadoc Comment
    public Map() {
        // Adds first Row
        this.mapData.add(new ArrayList<MapTile>());
    }


    /** Adds a new MapTile at it's assigned position as defined by xPos and yPos */
    public void addMapTile(MapTile newMapTile) {
        this.mapData.get(newMapTile.yPos).add(newMapTile.xPos, newMapTile);
    }
}

