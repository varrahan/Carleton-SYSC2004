import java.util.*;
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
 * @version March 03, 2023
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom = null; // previous room for "back" command
    private Stack<Room> rooms = new Stack<Room>();// stack of all rooms entered by user
    private int stackCount = 0; // number of elements in the stack
    private Item inventory; // item that is stored onto the user
    private Room beamRoom; // room to set for teleporation when beamer is charged
    private int eatCounter = 5; // once number hits zero, user can no longer pick up objects
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
        Room outside, theatre, pub, lab, office, transporterRoom;
        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        transporterRoom = new TransporterRoom("in the transporter room");
        
        // initialise room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.addItems("A warm cookie. Why this cookie is warm, I'll never know.", 0.1, "cookie");
        outside.addItems("A wiltering tree on the verge of death", 500.0, "tree");
        outside.addItems("A dried fountain ovegrown with weeds and vines", 150.0, "fountain");
        
        theatre.setExit("west", outside);
        theatre.addItems("A suspicious mask bearing the logo of the building", 2.0, "mask");
        theatre.addItems("A worn walking cane. There is a red substance dyed on the base", 6.1, "walking cane");
        theatre.addBeamer("A device capable of teleportation", 2.5, "beamer", false);
        
        pub.setExit("east", outside);
        pub.setExit("north", transporterRoom);
        pub.addItems("Someone must have enjoyed their last moments", 8.1, "bottle of whiskey");
        pub.addItems("A cup filled with coffee. It is still warm", 1.3, "cup");

        lab.setExit("north", outside);
        lab.setExit("east", office);
        lab.addItems("It's contents are spilled so there is no chance of seeing the temperature", 0.8, "thermometer");
        lab.addItems("A device that returns fond memories of highschool chemistry class", 17.6, "bunsen burner");
        lab.addBeamer("A device capable of teleportation", 2.5, "beamer", false);
        lab.addItems("A warm cookie. Why this cookie is warm, I'll never know.", 0.1, "cookie");

        
        office.setExit("west", lab);
        office.addItems("Just a regular pen. Nothing special", 0.3, "pen");
        office.addItems("This key seems to be able to unlock something", 0.7,"suspicious key");
        lab.addItems("A warm cookie. Why this cookie is warm, I'll never know.", 0.1, "cookie");

        transporterRoom.setExit("anywhere", ((TransporterRoom)transporterRoom).getExit(""));
        transporterRoom.addItems("A warm cookie. Why this cookie is warm, I'll never know.", 0.1, "cookie");
  
        currentRoom = outside;
        inventory = null;
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
        else if (commandWord.equals("take")) {
            take(command);
        }
        else if (commandWord.equals("drop")) {
            drop(command);
        }
        else if (commandWord.equals("charge")) {
            charge(command);
        }
        else if (commandWord.equals("fire")) {
            fire(command);
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
     * @param command The command to be processed
     */
    private void look(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Look what?");
        } else {
            System.out.println(currentRoom.getLongDescription());
            if(inventory != null){
                System.out.println("You have a " + inventory.name() + " in your inventory.");
            } else {
                System.out.println("You are not carrying anything");
            }
            }
    }
    
    /**
     * "eat" was entered. Outputs a message stating that the player is no
     * longer hungry.
     * @param command The command to be processed
     */
    private void eat(Command command){
        if(command.hasSecondWord()){
            System.out.println("Eat what?");
            return;
        }
        if(inventory == null || inventory.name().equals("cookie")== false )
        {
            System.out.println("You cannot eat what is in your inventory");
        } else {
           if(inventory.name().equals("cookie")){
               inventory = null;
               System.out.println("Cookie has been eaten. You feel energized");
               eatCounter = 5;
           }
            }
    }
    /**
     * "back" was entered. Moves the user back to the previous room that they
     * were in.
     * @param command The command to be processed
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
     * @param command The command to be processed
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
    /**
     * "take" was entered. Allows the user to pick up an item in the room as long
     * as they do not need to eat
     * @param command The command to be processed 
     */
    private void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Take what?");
        } else {
             if(inventory == null && eatCounter != 0){
                inventory = currentRoom.removeItem(command.getSecondWord());
                if(inventory == null){
                    System.out.println("Item does not exist in this room");
                } else {
                   System.out.println("You are holding a " + inventory.name()+".");
                   eatCounter--;
                }
             } else if(inventory == null && eatCounter == 0) {
                 System.out.println("You are too weak to take anything. Go find a cookie");
                 if(command.getSecondWord().equals("cookie")){
                    inventory = currentRoom.removeItem("cookie");
                    if(inventory == null){
                        System.out.println("There is no cookie in the room");
                    } else {
                        System.out.println("You are holding a cookie");
                    } 
                 }
             } else {
                System.out.println("You are already holding an item");
             }
        }
    }
    /**
     * "drop" was entered. Allows the user to drop an item in the current room.
     * @param command The command to be processed
     */
    private void drop(Command command){
        if(command.hasSecondWord()){
            System.out.println("Does not require second word");
            return;
        }
        if(inventory == null){
            System.out.println("You have nothing to drop");
        } else {
            currentRoom.inventoryAdd(inventory);
            System.out.println("You have dropped a " + inventory.name() + ".");
            inventory = null;
        }
    }
    /**
     * "charge" was entered. Allows the user to charge the beamer when it is in their inventory
     * @param command The command to be processed
     */
    private void charge(Command command){
        if(command.hasSecondWord()){
            System.out.println("Does not require second word");
            return;
        }
        if(inventory == null){
            System.out.println("There is nothing to charge");
        } else if(inventory.name().equals("beamer")){
            if(((Beamer)inventory).getCharge() == false){
                ((Beamer)inventory).chargeBeamer();
                beamRoom = currentRoom;
                System.out.println("Beamer has been charged");
            } else {
                System.out.println("Beamer is already charged");
            }
        } else {
            System.out.println("Object in inventory is not a beamer");
        }
        }
    /**
     * "fire" was entered. removes the beamers charge and transports the user to
     * the room that the beamer was charged in
     * @param command The command to be processed
     */
    private void fire(Command command){
        if(command.hasSecondWord()){
            System.out.println("Does not require second word");
            return;
        }
        if(inventory == null){
            System.out.println("Nothing is in the inventory");
            return;
        }
        if(inventory.name().equals("beamer")){
            if(((Beamer)inventory).getCharge() == false){
                System.out.println("Beamer is not charged");
            } else {
                ((Beamer)inventory).fireBeamer();
                currentRoom = beamRoom;
                System.out.println("Beamer has been fired");
            }
        } else {
            System.out.println("Object in inventory is not a beamer");
        }
    }
}


