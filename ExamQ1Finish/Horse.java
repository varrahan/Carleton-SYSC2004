
/**
 * The Horse class from Final Exam Review Questions Part 1
 *
 * @author Lynn Marshall
 * @version 1.0
 */
public class Horse extends Mammal
{
    private int age;
    /**
     * Constructor for objects of class Horse
     * 
     * @param name The animal/mammal/horse's name
     * @param weight The mammal/horse weight
     * @param numLegs The mammal/horse number of legs
     * @param age The horse's age
     */
    public Horse(String name, double weight, int numLegs, int age)
    {
        super(name,weight,numLegs);
        this.age = age;
    }

    /**
     * equals returns true if the two Horses have the same
     * name, weight, number of legs, and age
     * 
     * @param o the object to compare "this" to
     * @return true if the two horses are equal and false otherwise
     */
    public boolean equals (Object o) {
        if(this==o) {
            return true;
        }
        if (!(o instanceof Horse)) {
            return false;
        }
        Horse h = (Horse) o;
        return super.equals(h) && this.age==h.age;
    }
}
