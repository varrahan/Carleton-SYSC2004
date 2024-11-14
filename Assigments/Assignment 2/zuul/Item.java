
/**
 * Class item is used to create items that are stored in the room.
 *
 * @author Varrahan Uthayan 101229572
 * @version March 03, 2023

 */
public class Item
{
    // instance variables - replace the example below with your own
    private double weight; //weight of the item 
    private String description; //description of the item
    private String name; // name of object
    /**
     * Constructor for objects of class Item
     * @param weight The item's weight
     * @param description The item's description
     * @param name The name of the item
     */
    public Item(double weight, String description, String name)
    {
        this.weight = weight;
        this.description = description;
        this.name = name;
    }

    /**
     * Returns a string of the item name, description, and the weight 
     * of the item
     * @return a long description of the item
     */
    public String getDescription()
    {
        return "This is a " + name + " and it weighs " + weight + 
        " pounds." + description + ".\n";
    }
    /**
     * Returns a short description which includes just the name.
     * @return the name of the item
     */
    public String name()
    {
        return name;
    }
}
