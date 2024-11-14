import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version October 21, 2012
 * 
 * @author Varrahan Uthayan 101229572
 * @version March 03, 2023
 */

public class Room 
{
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> items;// stores an  arraylist of items in the room
    protected static ArrayList<Room> rooms = new ArrayList<Room>(); //stores an arraylist of rooms
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
        rooms.add(this);
    }
    /**
     * Define an exit from this room.
     * 
     * @param direction The direction of the exit
     * @param neighbour The room to which the exit leads
     */
    public void setExit(String direction, Room neighbour) 
    {
        exits.put(direction, neighbour);
    }

    /**
     * Returns a short description of the room, i.e. the one that
     * was defined in the constructor
     * 
     * @return The short description of the room
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a long description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     *     Items: There is a knife and is weighs 3.2 pounds.
     *     
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString() + ".\n"
        + getItems();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * 
     * @return Details of the room's exits
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    /**
     * Returns a string of items located inside the room.
     * @return Details of the item and it's weight
     */
    private String getItems()
    {
        String itemString = "Items: ";
        for(int i = 0; i < items.size(); i++){
            itemString += items.get(i).getDescription();
        }
        return itemString;
    }
        
    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction The exit's direction
     * @return The room in the given direction
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    /**
     * Adds Items to a room
     * @param description The item's description
     * @param weight The item's weight
     * @param name The short description/ name of the item
     */
    public void addItems(String description, double weight, String name){
        items.add(new Item(weight,description,name));
    }
    /**
     * Adds Items to a room
     * @param description The item's description
     * @param weight The item's weight
     * @param name The short description/ name of the item
     */
    public void addBeamer(String description, double weight, String name, boolean charge){
        items.add(new Beamer(weight,description,name, charge));
    }
    /** Removes and returns the item in a room
     * @param name The short description/ name of the item
     * 
     * @return returns null if object does not exist in the arraylist of items or
     * returns the removed item
     */
    public Item removeItem(String name){
        for(int i = 0; i < items.size(); i++){
        if(items.get(i).name().equals(name)){
            return items.remove(i);
        }
        }   
    return null;
    }
    /**
     * Adds item from inventory to a room
     * @param item the item to be added
     */
    public void inventoryAdd(Item item){
        items.add(item);
    }
}
