
/**
 * The Mammal class from Final Exam Review Questions Part 1
 *
 * @author Lynn Marshall
 * @version 1.0
 */
public class Mammal extends Animal
{
    protected double weight;
    protected int numLegs;
    /**
     * Constructor for objects of class Mammal: 3a
     * 
     * @param name The animal/mammal's name
     * @param weight The mammal's weight
     * @param numLegs The number of legs of the mammal
     *
     */
    public Mammal(String name, double weight, int numLegs)
    {
        super(name);
        this.weight = weight;
        this.numLegs = numLegs;
    }

    /**
     * equals returns true if the two Mammals have the same
     * name, weight, and number of legs
     * 
     * @param o the object to compare "this" to
     * @return true if the two mammals are equal and false otherwise
     */
    public boolean equals (Object o) {
        if(this==o) {
            return true;
        }
        if (!(o instanceof Mammal)) {
            return false;
        }
        Mammal m = (Mammal) o;
        return super.toString().equals(m.toString()) && weight==m.weight 
            && numLegs==m.numLegs;
    }
}
