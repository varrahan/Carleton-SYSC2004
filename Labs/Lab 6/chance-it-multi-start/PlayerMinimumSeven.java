/**
 * PlayerMinimumSeven models a player with the Chance It game strategy of
 * rolling the dice until the roll is 7 or higher.
 *
 * @author Varrahan Uthayan 101229572
 * @version Lab 6 Starting Point
 */
public class PlayerMinimumSeven extends Player
{
    /**
     * Constructor for objects of class PlayerMinimum7
     * 
     * @param dice The pair of dice shared by all players.
     * @param name The player's name.
     */
    public PlayerMinimumSeven(Dice dice, String name)
    {
        super(dice, name);
    }

    /** 
     * Encapsulates the strategy used by this player to determine when to 
     * stop rolling the dice during each turn.
     * 
     * All concrete subclasses must provide a strategy for the player
     * 
     * @return true when the player decides to end their current turn;
     *         otherwise returns false.
     */
    protected boolean stopRolling() {
        return currentRoll>=7;
    }
}
