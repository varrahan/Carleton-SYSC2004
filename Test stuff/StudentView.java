
/**
 * Write a description of interface StudentView here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public interface StudentView
{
    default void printStudentDetails(String stName, int stNumber){
        System.out.println("The student's name is " +stName+ ".");
        System.out.println("Their student number is "+stNumber+ ".");
    }
}
