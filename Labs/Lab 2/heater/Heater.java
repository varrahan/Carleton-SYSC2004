/**
 * A Heater models a simple space-heater. The operations provided by a Heater
 * object are:
 * 1. Increase and decrease the temperature setting by a set amount.
 * 2. Return the current temperature setting.
 * 3. Change the set amount by which the temperature is increased and lowered.
 * 
 * @author L.S. Marshall, SCE, Carleton University
 * (incomplete implementation for SYSC 2004 Lab 2)
 * @author Varrahan Uthayan, 101229572.
 * @version 1.03 January 23th, 2023
 */
public class Heater
{
    /** The temperature setting that the heater should maintain. */
    private int temperature;
    
    /** The temperature setting for a newly created heater. */
    private static final int INITIAL_TEMPERATURE = 15;
    
    /** 
     * The amount by which the temperature setting is raised/lowered when
     * warmer() and cooler() are invoked.
     */
     private int increment;
    
    /** 
     * The default amount by which the temperature setting is 
     * increased when warmer() is invoked and decreased when cooler()
     * is invoked.
     */
    private static final int DEFAULT_INCREMENT = 5;
    
    private int max;
    
    private static final int DEFAULT_MAX = 100;
    
    private int min;
    
    private static final int DEFAULT_MIN = 0;
    /**
     * Constructs a new Heater with an initial temperature setting of 15
     * degrees, and which increments and decrements the temperature
     * setting in increments of 5 degrees.
     */
    public Heater()
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        max = DEFAULT_MAX;
        min = DEFAULT_MIN;
    }
 
    /**
     * Write an appropriate comment here.
     */    
    public Heater(int minTemp, int maxTemp)
    {
        temperature = INITIAL_TEMPERATURE;
        increment = DEFAULT_INCREMENT;
        max = maxTemp;
        min = minTemp;
    }

    /**
     * Returns the current temperature of the heater.
     */    
    public int temperature()
    {
        return temperature;
    }
    
    /**
     * Increases the temperature by the value of the increment.
     */
    public void warmer()
    {
        if((temperature + increment) <= max){
        temperature = temperature + increment;
    }
    }

    /**
     * Decreases the temperature by the value of the increment.
     */    
    public void cooler()
    { 
        if((temperature - increment) >= min) {
        temperature = temperature - increment;
    }
    }
    
    
    /**
     * Assigns the increment a new value.
     */    
    public void setIncrement(int newIncrement)
    { 
        if(newIncrement > 0){
        increment = newIncrement;
    }
}
}
