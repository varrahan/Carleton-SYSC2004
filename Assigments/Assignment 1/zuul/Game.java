import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 * 
 * @author Lynn Marshall
 * @version October 21, 2012
 * 
 * @author Varrahan Uthayan 101229572
 * @version January 22, 2022
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom = null; // previous room for "back" command
    private Stack<Room> rooms = new Stack<Room>();// stack of all rooms entered by user
    private int stackCount = 0; // number of elements in the stack
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office;
      
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.addItems("willow tree", 500.0);
        outside.addItems("fountain", 150.0);
        
        theatre.setExit("west", outside);
        theatre.addItems("mask", 2.0);
        theatre.addItems("walking cane", 6.1);
        
        pub.setExit("east", outside);
        pub.addItems("bottle of whiskey", 8.1);
        pub.addItems("cup", 1.3);

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.addItems("thermometer", 0.8);
        lab.addItems("bunsen burner", 17.6);
        

        office.setExit("west", lab);
        office.addItems("pen", 0.3);
        office.addItems("suspicious key", 0.7);

        currentRoom = outside;
        rooms.push(currentRoom);// start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * 
     * @param command The command to be processed
     * @return true If the command ends the game, false otherwise
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("look")) {
            look(command);
        }
        else if (commandWord.equals("eat")) {
            eat(command);
        }
        else if (commandWord.equals("back")) {
            back(command);
        }
        else if (commandWord.equals("stackBack")) {
            stackBack(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print a cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        int n = parser.getCommands().length;
        for(int i = 0; i < n; i++){
            System.out.println(parser.getCommands()[i]);
        }
        }
        
    

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * 
     * @param command The command to be processed
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            previousRoom = currentRoom;
            currentRoom = nextRoom;
            rooms.push(currentRoom);
            stackCount++;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * 
     * @param command The command to be processed
     * @return true, if this command quits the game, false otherwise
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * "look" was entered. Gives a detailed description of the current room
     * the user is in.
     */
    private void look(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Look what?");
        } else {
            System.out.println(currentRoom.getLongDescription());
            }
    }
    
    /**
     * "eat" was entered. Outputs a message stating that the player is no
     * longer hungry.
     */
    private void eat(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Eat what?");
        } else {
            System.out.println("You are no longer hungry");
            }
    }
    /**
     * "back" was entered. Moves the user back to the previous room that they
     * were in.
     */
    private void back(Command command){
        Room tempRoom = currentRoom;
        if(command.hasSecondWord()) {
            System.out.println("Back what?");
        } else {
            if(previousRoom == null){
                System.out.println("You are at the beginning so there is no previous room.");
                System.out.println(currentRoom.getLongDescription());
            }else{ 
                currentRoom = previousRoom;
                previousRoom = tempRoom;
                System.out.println("You have gone back.");
                System.out.println(currentRoom.getLongDescription());
        }
    }
    }
    /**
     * "stackBack" was entered. Moves the user back to the previous rooms
     * they were in as far as the user likes until the user is back at the 
     * beginning of the game.
     */
    private void stackBack(Command command){
        if(stackCount != 0){
            rooms.pop();
            currentRoom = rooms.peek();
            stackCount--;
            System.out.println(currentRoom.getLongDescription());
        }
        else{
            System.out.println("No room to go back to, you are back to the beginning.");
        }
    }
}

