import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

/**
 * This is a TicTacToe game
 *
 * @author Varrahan Uthayan 101229572
 * @version April 8, 2023
 */
public class TicTacToe extends JFrame implements ActionListener
{
    private JPanel topP;
    private JLabel topL;
    private JPanel mainP;
    private JButton[][] mainB;
    private JPanel lowP;
    private JLabel lowL;
    private int games;
    private int xWon;
    private int oWon;
    private int ties;
    private boolean oTurn;
    private int rounds;
    private boolean won;
    private JMenuBar bar;
    private JMenu game;
    private JMenuItem play;
    private JMenuItem quit;
    private JMenuItem reset;
    private JMenuItem help;
    private Help hFrame;
    /**
     * Constructor for objects of class TicTacToe
     */
    public TicTacToe()
    {
        Container FCP = this.getContentPane();
        FCP.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(500,500);
        title();
        main();
        games = 0;
        xWon = 0;
        oWon = 0;
        ties = 0;
        low();
        oTurn = true;
        bar = new JMenuBar();
        game = new JMenu("Game");
        play = new JMenuItem("New");
        reset = new JMenuItem("Reset");
        help = new JMenuItem("Help");
        quit = new JMenuItem("Quit");
        play.addActionListener(this);
        help.addActionListener(this);
        reset.addActionListener(this);
        quit.addActionListener(this);
        game.add(play);
        game.add(reset);
        game.add(help);
        game.add(quit);
        bar.add(game);
        this.setJMenuBar(bar);
        this.setVisible(true);
    }
    /**
     * Sets up the board to play the game. Changes the title to whatever player
     * is currently playing.
     */
    public void play() {
        if(oTurn) {
            topL.setText("Game in progress, O Turn");
        }
        else {
            topL.setText("Game in progress, X Turn");
        }
        rounds = 0;
        won = false;
        for(int row = 0; row < mainB.length; row++) {
            for(int col = 0; col < mainB[row].length; col++) {
                mainB[row][col].setEnabled(true);
                mainB[row][col].setText(null);
            }
        }
    }
    /**
     * Creates a label that is set at the top of the panel to act as a title
     * for the game
     */
    private void title() {
        topP = new JPanel();
        topL = new JLabel();
        topL.setText("Tic-Tac-Toe");
        topP.setBackground(Color.white);
        topL.setFont(new Font("Arial", Font.PLAIN, 25));
        topP.add(topL);
        this.add(topP, BorderLayout.NORTH);
    }
    /**
     * Builds the gui for the help button
     */
    private void help(){
        hFrame = new Help();
    }
    /**
     * Creates the tic-tac-toe board
     */
    private void main() {
        mainP = new JPanel();
        mainB = new JButton[3][3];
        mainP.setLayout(new GridLayout(3,3,3,3));
        for(int row = 0; row < mainB.length; row++) {
            for(int col = 0; col < mainB[row].length; col++) {
                mainB[row][col] = new JButton();
                mainB[row][col].setFocusable(false);
                mainB[row][col].addActionListener(this);
                mainB[row][col].setEnabled(false);
                mainP.add(mainB[row][col]);
            }
        }
        this.add(mainP, BorderLayout.CENTER);
    }
    /**
     * Method used to create the lower panel of the tic-tac-toe game. This panel
     * includes how many times X won, how many times O won, and how many tied games
     * there were in a session
     */
    private void low() {
        lowP = new JPanel();
        lowL = new JLabel();
        lowP.setBackground(Color.white);
        lowL.setFont(new Font("Arial", Font.PLAIN, 15));
        lowL.setText("Games: " + games + ", " + "X Won: " + xWon + ", " + "O Won: " + oWon + ", " + "Ties: " + ties);
        lowP.add(lowL);
        this.add(lowP, BorderLayout.SOUTH);
    }
    /**
     * Method used to perform all the actions in the game involving the buttons.
     * This includes playing a new game, quiting the game, and what space the player
     * is placing down their mark (X or O). Method also updates the botton label's
     * scores.
     *
     * @param e The button that is being pressed.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JMenuItem) {
            JMenuItem emi = (JMenuItem) e.getSource();
            if(emi == play) {
                play();
            }
            else if(emi == reset) {
                games = 0;
                xWon = 0;
                oWon = 0;
                ties = 0;
                lowL.setText("Games: " + games + ", " + "X Won: " + xWon + ", " + "O Won: " + oWon + ", " + "Ties: " + ties);
                play();
            }
            else if (emi == help) {
                help();
            }
            else if(emi == quit) {
                this.dispose();
                System.exit(0);
            }
        }
        else if (e.getSource() instanceof JButton) {
            JButton eb = (JButton) e.getSource();
            for(int row = 0; row < mainB.length; row++) {
                for(int col = 0; col < mainB[row].length; col++) {
                    if(eb == mainB[row][col]) {
                        if(oTurn) {
                            mainB[row][col].setEnabled(false);
                            mainB[row][col].setFont(new Font("Arial", Font.BOLD, 30));
                            mainB[row][col].setText("O");
                            topL.setText("Game in progress, X Turn");
                            oTurn = false;
                            rounds++;
                        }
                        else {
                            mainB[row][col].setEnabled(false);
                            mainB[row][col].setFont(new Font("Arial", Font.BOLD, 30));
                            mainB[row][col].setText("X");
                            topL.setText("Game in progress, O Turn");
                            oTurn = true;
                            rounds++;
                        }
                        if(rounds > 4) {
                            if(mainB[row][0].getText() == mainB[row][1].getText() && mainB[row][0].getText() == mainB[row][2].getText()) {
                                won = true;
                            }
                            else if (mainB[0][col].getText() == mainB[1][col].getText() && mainB[0][col].getText() == mainB[2][col].getText()) {
                                won = true;
                            }
                            else if(row == col) {
                                if (mainB[0][0].getText() == mainB[1][1].getText() && mainB[0][0].getText() == mainB[2][2].getText()) {
                                    won = true;
                                }
                            }
                            else if (row==2-col) {
                                if (mainB[0][2].getText() == mainB[1][1].getText() && mainB[0][2].getText() == mainB[2][0].getText()) {
                                    won = true;
                                }
                            }
                        }
                        if(won) {
                            games++;
                            for(int rowTwo = 0; rowTwo < mainB.length; rowTwo++) {
                                for(int colTwo = 0; colTwo < mainB[rowTwo].length; colTwo++) {
                                    mainB[rowTwo][colTwo].setEnabled(false);
                                }
                            }
                            if(oTurn) {
                                topL.setText("Game over, X Won");
                                xWon++;
                            }
                            else {
                                topL.setText("Game over, O Won");
                                oWon++;
                            }
                            lowL.setText("Games: " + games + ", " + "X Won: " + xWon + ", " + "O Won: " + oWon + ", " + "Ties: " + ties);
                        }
                        else if(rounds == 9) {
                            ties++;
                            games++;
                            for(int rowTwo = 0; rowTwo < mainB.length; rowTwo++) {
                                for(int colTwo = 0; colTwo < mainB[rowTwo].length; colTwo++) {
                                    mainB[rowTwo][colTwo].setEnabled(false);
                                }
                            }
                            topL.setText("Game over, Tie");
                            lowL.setText("Games: " + games + ", " + "X Won: " + xWon + ", " + "O Won: " + oWon + ", " + "Ties: " + ties);
                        }
                    }
                }
            }
        }
    }
}
