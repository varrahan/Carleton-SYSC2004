
/**
 * Write a description of class Item here.
 *
 * @author Varrahan Uthayan 101229572
 * @version January 22, 2022

 */
public class Item
{
    // instance variables - replace the example below with your own
    private double weight; //weight of the item 
    private String description; //description of the item

    /**
     * Constructor for objects of class Item
     * @param weight The item's weight
     * @param description The item's description
     */
    public Item(double weight, String description)
    {
        this.weight = weight;
        this.description = description;
    }

    /**
     * Returns a string of the item description and the weight of the item
     */
    public String getDescription()
    {
        return "This is a " + description + " and it weighs " + weight + 
        " pounds.\n";
    }
}
