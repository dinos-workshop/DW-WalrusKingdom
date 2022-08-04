import java.util.HashMap;

/** Manages loading all trigger types and makes them easily accessible */
public class TriggerManager {

    /** The list of all known Triggers, stored in a HashMap for easy access via Name */
    HashMap<String, Trigger> TRIGGERS = new HashMap<String, Trigger>();



    /** The default constructor which initialises the List of Triggers */
    public TriggerManager() {

        // Iterate over all Triggers
        System.out.println("Loading Trigger Data");
        for (Trigger trigger : Trigger.values()) {

            // Add each of them to the HashMap for easy access
            this.addTrigger(trigger);
        }

        // All Triggers have been added
        System.out.println(" Done");
    }



    /** Adds a new Trigger to the MapMaterial's Triggers */
    public void addTrigger(Trigger trigger) {

        // Add new Trigger type as well as its Name to the Trigger Hashmap
        TRIGGERS.put(trigger.name(), trigger);
        System.out.print('.');
        //System.out.print("'" + trigger.name() + "' has been added");
    }



    /** Will return a Trigger Object as referenced by its Name */
    public Trigger getTriggerByName(String name) {

        // Get the right Trigger Object from the Hashmap and return it
        return TRIGGERS.get(name);
    }

}
