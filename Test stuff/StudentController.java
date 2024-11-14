
/**
 * Write a description of class StudentController here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentController implements StudentView
{
    private Student student;
    private StudentView view;
    /**
     * Constructor for objects of class StudentController
     */
    public StudentController(Student student, StudentView view)
    {
       this.student = student; 
       this.view = view;
    }
    public void setStudentName(String name){
        student.setName(name);
    }
    public String getStudentName(){
        return student.getName();
    }
    public void setStudentNumber(int number){
        student.setNumber(number);
    }
    public int getStudentNumber(){
        return student.getNumber();
    }
    public void updateView(StudentView view){
        view.printStudentDetails(getStudentName(), getStudentNumber());
    }
}
