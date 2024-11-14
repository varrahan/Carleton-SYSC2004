
/**
 * The Cat class from Final Exam Review Questions Part 1
 *
 * @author Lynn Marshall
 * @version 1.0
 */
public class Cat extends Mammal
{
    private String colour;
    /**
     * Constructor for objects of class Cat: 3b
     * 
     * @param name The animal/mammal/cat's name
     * @param weight The mammal/cat weight
     * @param numLegs The mammal/cat number of legs
     * @param colour The cat's colour
     */
    public Cat(String name, double weight, int numLegs, String colour)
    {
        super(name,weight,numLegs);
        this.colour = colour;
    }
    
    /**
     * toString returns a string representation of the cat as:
     * "This cat is a <colour> <name>. 
     * It has <numLegs> legs, and a weight of <weight>kgs."
     * 
     * @return the string representation
     */
    public String toString() {
        return "This cat is a " + colour + " " + super.toString() 
            + ".\nIt has " + super.numLegs + " legs, and a weight of " 
            + weight + "kgs."; // super optional for numLegs and weight
            // can also put "this" in front of colour, numLegs, and
            // weight.
    }
    
}
