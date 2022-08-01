public class window {

    /** Main class which will be called launching the game */
    public static void main(String[] args) {

        // Initialise Material Manager so different Material Types can be handled
        System.out.println("Initialising Material Manager");
        MaterialManager materialManager = new MaterialManager("./assets/material.json");

        System.out.println("Loading Material Data");
        materialManager.loadMaterials();

        System.out.println("Saving Material Data");
        materialManager.saveMaterials("./assets/material_OUT.json");

        System.out.println("Done");
        // Initialise Map Manager so map data can be handled
        // TODO: Implement map manager
    }
}


/*
map(
    for each x, y...
    tile[x][y](
        foreground(
            material
            material
            [...]
        )
        background(
            material
            material
            [...]
        )
    )
)
 */



/*
    For each tool/weapon add a list of materialIDs that can be destroyed with them - plus a timer on how many steps need to be taken.
    will need additional tiles for damaged blocks. Maybe clones of the entire tile sources image for each level of damage?
    Add damage/health map for the entire map to overlay...?
    --> add to main map data
 */