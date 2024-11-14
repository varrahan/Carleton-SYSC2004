
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
/**
 *This is gui for the help pop-up
 *
 * @author Varrahan Uthayan 101229572
 * @version March 08, 2023
 */
public class Help
{
    // instance variables - replace the example below with your own
    private JFrame frame;
    private JTextField text;
    

    /**
     * Constructor for objects of class Help
     */
    public Help()
    {
        frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.setSize(320,270);
        frame.setTitle("Help Menu");
        text = new JTextField("Help Menu");
        text.setFont(new Font("Times New Roman", Font.BOLD, 22));
        text.setHorizontalAlignment(JTextField.CENTER);
        text.setFocusable(false);
        text.setEditable(false);
        frame.add(text, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
