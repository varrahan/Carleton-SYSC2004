
/**
 * Subclass extends from class Item. Class is used to create the beamer object
 * that is stored in rooms
 *
 * @author Varrahan Uthayan 101229572
 * @version March 03, 2023
 */
public class Beamer extends Item
{
    private boolean charge; //True if the beamer is charged, false if it is not charged
    /**
     * Constructor for objects of class Beamer
     * @param weight The weight of the item
     * @param description A long description of the item
     * @param name The name of the object
     * @param charge The charge of the beamer. 
     */
    public Beamer(double weight, String description, String name, boolean charge)
    {
        super(weight, description, name);
        this.charge = charge;
    }
    
    /**
     * Charges the beamer so that the beamer can fire
     *
     */
    public void chargeBeamer()
    {
        charge = true;
    }
    
    /**
     * Fires the beamer and sets charge to false
     */
    public void fireBeamer()
    {
        charge = false;
    }
    
    /**
     * Checks to see if beamer has already been charged or not
     * @return returns the value of charge
     */
    public boolean getCharge(){
        return charge;
    }
}

