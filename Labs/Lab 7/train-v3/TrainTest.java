import java.util.ArrayList;

/**
 * The test class TrainTest.
 *
 * @author  Varrahan Uthayan 101229572
 * @version May 2015
 */
public class TrainTest extends junit.framework.TestCase
{
    private Train aTrain;
    
    private Car car1, car2, car3, car4;

    /**
     * Default constructor for test class TrainTest
     */
    public TrainTest()
    {
        aTrain = new Train();
        
        car1 = new Car(1250, true);
        aTrain.addCar(car1);
        
        car2 = new Car(1300, false);
        aTrain.addCar(car2);
        
        car3 = new Car(1740, false);
        aTrain.addCar(car3);
        
        car4 = new Car(2023, true);
        aTrain.addCar(car4);

    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
 
    public void testCreateEmptyTrain()
    {
        Train aTrain = new Train();
        
        /* Verify that a new train has no cars. */
        assertEquals(0, aTrain.cars().size());
    }
    
    public void testAddCar()
    {
        ArrayList<Car> cars = aTrain.cars();
        assertEquals(4, cars.size());
        
        
        /* Verify that each car added to the train was placed at
         * the end of the list.
         */
        Car aCar;
        aCar = cars.get(0);
        
        /* Important - assertSame() does not compare the Car objects 
         * referred to by car1 and aCar to detemine if they are equal
         * (have the same state). It verifies that car1 an aCar refer to
         * the same object; i.e., that the Car (reference) retrieved by get(0)
         * is the first first that was added to the ArrayList.
         */
        assertSame(car1, aCar);
        
        aCar = cars.get(1);
        assertSame(car2, aCar);
        
        aCar = cars.get(2);
        assertSame(car3, aCar);
        
        aCar = cars.get(3);
        assertSame(car4, aCar);
    }
        
    public void testIssueTicket()
    {
        /* Book all the seats in the business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Attempt to book one more business-class seat, even
         * though they are all booked.
         */
        ArrayList<Car> cars = aTrain.cars();
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertFalse(cars.get(3).seats()[i].isBooked());
        }
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertFalse(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertFalse(cars.get(3).seats()[i].isBooked());
        }
        /* Book all the seats in the second economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertFalse(cars.get(3).seats()[i].isBooked());
        }
        
        /* Bool all the seats in the second business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* check that all seats are now booked */
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(0).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(1).seats()[i].isBooked());
        }
        
        for (int i = 0; i < Car.ECONOMY_SEATS; i++) {
            assertTrue(cars.get(2).seats()[i].isBooked());
        }  
        
        for (int i = 0; i < Car.BUSINESS_SEATS; i++) {
            assertTrue(cars.get(3).seats()[i].isBooked());
        }
    }
    
    public void testCancelTicket()
    {
        /* Book all the seats in the first business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* Book half the seats in the second business-class car*/
        for (int i = 0; i < Car.BUSINESS_SEATS/2 ; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        ArrayList<Car> cars = aTrain.cars();
        
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());        
        
        /* Cancel ticket in a non-existent car. */
        assertFalse(aTrain.cancelTicket(1500, 7));
        
        /* Cancel ticket in a non-existent seat. */
        assertFalse(aTrain.cancelTicket(1300, 54));
        
        /* Cancel ticket for a seat that hasn't been booked. */
        assertFalse(aTrain.cancelTicket(1740, 21));
        assertFalse(cars.get(2).seats()[20].isBooked());        
        
        /* Attempt to cancel the same ticket twice. */
        assertTrue(aTrain.cancelTicket(1250, 11));
        assertFalse(cars.get(0).seats()[10].isBooked());
        
        assertFalse(aTrain.cancelTicket(1250, 11));   
        assertFalse(cars.get(0).seats()[10].isBooked()); 
        
        assertTrue(aTrain.cancelTicket(2023, 10));
        assertFalse(cars.get(3).seats()[9].isBooked()); 
        
        assertFalse(aTrain.cancelTicket(2023, 22));
        assertFalse(cars.get(3).seats()[21].isBooked()); 
    }
    
    public void testBookCancelTicket()
    {
        /* Book all the seats in the first business-class car. */
        for (int i = 0; i <Car.BUSINESS_SEATS; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        /* Book all the seats in the first economy-class car. */
        for (int i = 0; i <Car.ECONOMY_SEATS; i++) {
            assertTrue(aTrain.issueTicket(false));
        }
        
        /* Book half the seats in the second business-class car*/
        for (int i = 0; i < Car.BUSINESS_SEATS/2 ; i++) {
            assertTrue(aTrain.issueTicket(true));
        }
        
        ArrayList<Car> cars = aTrain.cars();
        
        /* Cancelling 3 economy tickets */
        assertTrue(aTrain.cancelTicket(1300, 4));
        assertFalse(cars.get(1).seats()[3].isBooked());
        
        assertTrue(aTrain.cancelTicket(1300, 19));
        assertFalse(cars.get(1).seats()[18].isBooked());
        
        assertTrue(aTrain.cancelTicket(1300, 23));
        assertFalse(cars.get(1).seats()[22].isBooked());
        
        /* Booking 4 economy tickets*/
        for (int i = 0; i < 4 ; i++){
            assertTrue(aTrain.issueTicket(false));
        }
        
        assertTrue(cars.get(1).seats()[3].isBooked());
        assertTrue(cars.get(1).seats()[18].isBooked());
        assertTrue(cars.get(1).seats()[23].isBooked());
        assertTrue(cars.get(2).seats()[0].isBooked());
        
        /* Cancelling 3 business tickets */
        assertTrue(aTrain.cancelTicket(1250, 10));
        assertFalse(cars.get(0).seats()[9].isBooked());
        
        assertTrue(aTrain.cancelTicket(2023, 1));
        assertFalse(cars.get(3).seats()[0].isBooked());
        
        assertTrue(aTrain.cancelTicket(2023, 3));
        assertFalse(cars.get(3).seats()[2].isBooked());
        
        /* Booking 4 business tickets*/
        for (int i = 0; i < 4 ; i++){
            assertTrue(aTrain.issueTicket(true));
        }
        
        assertTrue(cars.get(0).seats()[9].isBooked());
        assertTrue(cars.get(3).seats()[0].isBooked());
        assertTrue(cars.get(3).seats()[2].isBooked());
        assertTrue(cars.get(3).seats()[15].isBooked());
    }
}
