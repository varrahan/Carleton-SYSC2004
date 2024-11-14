/**
 * Car models a train car. The car can either be a business class car,
 * which costs $125.00 but has only 30 seats, or an economy class car,
 * which costs only $50.00 but has 40 seats
 * 
 * @author Varrahan Uthayan 101229572
 * @version 1.1 January 18, 2023
 */
public class Car
{
    /** This car's identifier. */
    private int id;
   
    /**
     * true == this car is a business-class car,
     * false == this car is an economy-class car.
     */
    private boolean businessClass;
    
    private int numSeats; // The number of seats that the car has
    
    private double price; // The price of the seats of the car
    
    /** The cost of a business class seat, in dollars. */
    public static final double BUSINESS_SEAT_COST = 125.0;
    
    /** The cost of an economy class seat, in dollars. */
    public static final double ECONOMY_SEAT_COST = 50.0;    
  
    /** The number of seats in a business class car. */
    public static final int BUSINESS_SEATS = 30;   
    
    /** The number of seats in an economy class car. */
    public static final int ECONOMY_SEATS = 40;   
   
    /** The list of this car's seats. */
    private Seat[] seats;
   
    /**
     * Constructs a new Car object with the specified id.
     * If parameter isBusinessClass is true, the car is a business-class
     * car. If parameter isBusinessClass is false, the car is an
     * economy-class car.
     * @param carId The specific identification number of the car
     * @param isBusinessClass True if the car is business class and false if 
     * it is economy class
     */
    public Car(int carId, boolean isBusinessClass)
    {
        id = carId;
        if(isBusinessClass == true){
            businessClass = true;
            numSeats = BUSINESS_SEATS;
            price = BUSINESS_SEAT_COST;
        } else {
            businessClass = false;
            numSeats = ECONOMY_SEATS;
            price = ECONOMY_SEAT_COST;
        }
        seats = new Seat[numSeats];
        for(int i = 0; i < numSeats; i++){
            seats[i] = new Seat(i+1, price);
        }
    }

    /**
     * Returns this car's list of seats. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return The seats in this car, an array of Seat objects.
     */
    public Seat[] seats()
    {
        return seats;
    }
 
    /** 
     * @return Returns true if this is a business-class car and
     * false if this is an economy-class car.
     */
    public boolean isBusinessClass()
    {
        if(businessClass == true){
            return true;
        } else {
            return false;
        }
    }
 
    /**
     * @return Returns the id of this car.
     */
    public int id()
    {
        return id;
    }
  
    /**
     * This method is called when the specified seat has been booked,
     * to print a ticket for that seat.
     * 
     * @param seatNo The integer identifier of the seat.
     */
    private void printTicket(int seatNo)
    {
        System.out.println("Car ID: " + id);
        System.out.println("Seat number: " + seatNo);
        System.out.println("Price: ");
        if (businessClass) {
            System.out.println(BUSINESS_SEAT_COST);
        } else {
            System.out.println(ECONOMY_SEAT_COST);
        }
    }   
 
    /**
     * Attempts to book a seat. If successful, this method prints a 
     * ticket and returns true.
     * If no seats are available, this method returns false.
     * @return Returns true if seat available and booked
     * @return Returns false if no seats available in car
     */
    public boolean bookNextSeat()
    {
        // After booking an available seat, print the ticket by calling
        // private method printTicket(); e.g.,
        // printTicket(seats[i].number());
        boolean booked;
        for(int i = 0; i < numSeats; i++){
            booked = seats[i].isBooked();
            if(booked == false){
            seats[i].book();
            printTicket(seats[i].number());
            return true;
            }
        }
        return false;
    }
        
    /** 
     * Cancels the booking for the specified seat, which must be between
     * 1 and the maximum number of seats in the car.
     * If the seat number is valid and if the seat has been reserved, this
     * method cancels the booking for that seat and returns true. 
     * If the seat number is not valid, this method returns false. 
     * If the seat number is valid, but the seat has not been reserved, 
     * this method returns false.
     * @param seatNo seat number that is to be cancelled 
     * @return Returns false if seat number is not valid and if seat is not booked
     * @return Returns true if seat number is valid and booked
     */
    public boolean cancelSeat(int seatNo)
    {
    if(seatNo > numSeats || seatNo < 1){
        return false;
    }
    if(seats[seatNo-1].isBooked() == true){
        seats[seatNo-1].cancelBooking();
        return true;
    }
    return false;
    }
}

