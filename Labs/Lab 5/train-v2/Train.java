/**
 * Train models a train. Train cars can individually be added via the addCar 
 * function, where the user can select wheter the train is economy or business
 * class, and what the car id is.
 * 
 * @author Varrahan Uthayan 101229572
 */
import java.util.ArrayList;


public class Train

{
    /** The cars in this train. */
    private ArrayList<Car> cars;
   
    /** 
     * Constructs an empty train; i.e., one that has no cars.
     */
    public Train()
    {
        cars = new ArrayList<Car>();
    }
   
    /**
     * Adds the specified car to this train.
     * @param car the train car that is being added to the train
     */
    public void addCar(Car car)
    {
       cars.add(car); 
    }
    
    
    
    /**
     * Returns this trains's list of cars. This method is intended for 
     * testing purposes only, and should not be called by other objects,
     * as it may be removed from the final version of this class.
     * 
     * @return A list of the cars in the train
     */
    public ArrayList<Car> cars()
    {
        return cars;
    }    
      
    /**
     * Attempts to issue a ticket for a business class seat or an
     * economy class seat, as specified by the method's argument.
     * It will attempt to book a seat in the first car of the appropriate
     * type, but if a seat is not available it will attempt to book a seat
     * in the second car of the appropriate type, and so on. 
     * A request to issue a ticket in a business-class car will never result
     * in a seat being reserved in an economy-class car, and vice-versa. 
     * @param businessClassSeat determines whether the ticket is a business class
     * ticket or if it is a economy ticket. True if business false if economy.
     * @return Returns true if successful, false otherwise.
     */
    public boolean issueTicket(boolean businessClassSeat)
    {
        boolean booked;
        for(int i = 0; i < cars.size(); i++){
            if(cars.get(i).isBusinessClass() == businessClassSeat){
                booked = cars.get(i).bookNextSeat();
                if(booked == true){
                    return true;
                }
            }
        }
        return false;
    }
    
    
   
    /**
     * Cancels the ticket for the specified seat in the specified car.
     * @param carId the id of the car where the desired seat is to be cancelled
     * @param seatNo the seat number that the user wants to cancel
     * @return Returns true if successful, false otherwise.
     */
    public boolean cancelTicket(int carId, int seatNo)
    {
    boolean cancel;
    for(int i = 0; i < cars.size(); i++){
        if(cars.get(i).id() == carId){
            cancel = cars.get(i).cancelSeat(seatNo);
            if(cancel == true){
                return true;
            }
        }
    }
    return false;
    }
    }


