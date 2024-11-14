import java.util.*;
/**
 * SubClass of Class Room. Represents the Transporter room, which transports the user
 * to a random room in the game.
 *
 * @author Varrahan Uthayan 101229572
 * @version March 03, 2023
 */
public class TransporterRoom extends Room
{
    /**
     * Constructor for objects of class TransporterRoom
     * @param description The description of the room
     */
    public TransporterRoom(String description)
    {
        super(description);
    }
    /**
    * Returns a random room, independent of the direction parameter.
    *
    * @param direction Ignored.
    * @return A randomly selected room.
    * @Overrite
    */ 
    public Room getExit(String direction)
    {
        return findRandomRoom();
    }
    /**
    * Choose a random room.
    *
    * @return The room we end up in upon leaving this one.
    */
    private Room findRandomRoom()
    {
        Random rand = new Random();
        int x = rand.nextInt(rooms.size());
        return rooms.get(x);
    }
}
