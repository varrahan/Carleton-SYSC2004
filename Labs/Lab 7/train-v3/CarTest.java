/**
 * The test class CarTest.
 *
 * @author  Varrahan Uthayan 101229572
 * @version 1.2 May 1st, 2015
 */
public class CarTest extends junit.framework.TestCase
{
    /**
     * Default constructor for test class CarTest
     */
    public CarTest()
    {
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
    
    public void testCreateBusinessCar()
    {
        Car aCar = new Car(1385, true);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */
        assertEquals(Car.BUSINESS_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.BUSINESS_SEAT_COST, seats[i].price());
        }
    }
    
    public void testCreateEconomyCar()
    {
        Car aCar = new Car(1400, false);
        Seat[] seats = aCar.seats();
        
        /* Verify that the car has the right number of seats. */        
        assertEquals(Car.ECONOMY_SEATS, seats.length);
        
        /* Verify that each seat has the correct number and price. */       
        for (int i = 0; i < seats.length; i++) {
            assertEquals(i+1, seats[i].number());
            assertEquals(Car.ECONOMY_SEAT_COST, seats[i].price());
        }
    }    
    
    public void testID()
    {
         Car aCar;
         aCar= new Car(1385, true);
         assertEquals(1385, aCar.id());
         aCar = new Car(1400, false);
         assertEquals(1400, aCar.id());
    }
    
    public void testIsBusinessClass()
    {
         Car aCar;
         aCar = new Car(1385, true);
         assertTrue(aCar.isBusinessClass());
         aCar = new Car(1400, false);
         assertFalse(aCar.isBusinessClass()); 
    }
    
    public void testBookNextSeat()
    {
        Car aCar, bCar;
        aCar = new Car(1234, true);
        bCar = new Car(3572,false);
        
        Seat[] aSeats = aCar.seats();
        Seat[] bSeats = bCar.seats();
        /* Verify that no seats are booked. */
        for (int i = 0; i < aSeats.length; i++) {
            assertFalse(aSeats[i].isBooked());
        }        
        
        for (int i = 0; i < bSeats.length; i++) {
            assertFalse(bSeats[i].isBooked());
        } 
        /* Verify that the seats are booked consecutively,
         * starting with Seat #1.
         */
        for (int i = 0; i < aSeats.length; i++) {
            aSeats = aCar.seats();
            assertFalse(aSeats[i].isBooked()); // not booked
            assertTrue(aCar.bookNextSeat()); // book it
            assertTrue(aSeats[i].isBooked()); // now booked
            if (i!=aSeats.length-1) {
                assertFalse(aSeats[i+1].isBooked()); // but next isn't
            }
        }
        
        for (int i = 0; i < bSeats.length; i++) {
            bSeats = bCar.seats();
            assertFalse(bSeats[i].isBooked()); // not booked
            assertTrue(bCar.bookNextSeat()); // book it
            assertTrue(bSeats[i].isBooked()); // now booked
            if (i!=bSeats.length-1) {
                assertFalse(bSeats[i+1].isBooked()); // but next isn't
            }
        }
        /* Try to book a seat now that all the seats have been booked. */
        assertFalse(aCar.bookNextSeat());
        assertFalse(bCar.bookNextSeat());
    }
    
    public void testCancelSeat()
    {
        Car aCar, bCar;
        aCar = new Car(1234, true);
        bCar = new Car(3572,false);
        
        Seat[] aSeats = aCar.seats();
        Seat[] bSeats = bCar.seats();
        
        /* Cancel seat 0. cancelSeat() should return false, as there
         * is no seat 0.
         */
        assertFalse(aCar.cancelSeat(0));
        assertFalse(bCar.cancelSeat(0));
        /* Cancel seat 50. cancelSeat() should return false, as there is no
         * seat 50
         */
        assertFalse(aCar.cancelSeat(50));
        assertFalse(bCar.cancelSeat(50));
        /* Try cancelling a seat whose number is one higher than 
         * the highest valid seat number (seats.length - 1). 
         * cancelSeat() should return false.
         */
        assertFalse(aCar.cancelSeat(aSeats.length));
        assertFalse(bCar.cancelSeat(bSeats.length));
        /* Try cancelling all the seats in the car, even though 
         * they haven't been booked. cancelSeat() should 
         * return false.
         */
        for (int i = 0; i < aSeats.length; i++) {
            assertFalse(aCar.cancelSeat(i+1));
        }
        
        for (int i = 0; i < bSeats.length; i++) {
            assertFalse(bCar.cancelSeat(i+1));
        }
        /* Book all the seats */
        for (int i = 0; i < aSeats.length; i++) {
            aCar.bookNextSeat();
        }  
        
        for (int i = 0; i < bSeats.length; i++) {
            bCar.bookNextSeat();
        } 
        /* Try cancelling all the seats in the car.
         */
        for (int i = 0; i < aSeats.length; i++) {
            assertTrue(aCar.cancelSeat(i+1));
        } 
        
        for (int i = 0; i < bSeats.length; i++) {
            assertTrue(bCar.cancelSeat(i+1));
        }
        /* In case seat numbers are off, try some more tests.
         */
        Car cCar;
        cCar = new Car (4321,false);
        // book 2 seats
        assertTrue(cCar.bookNextSeat());
        assertTrue(cCar.bookNextSeat());
        // try to cancel the 3rd (not booked)
        assertFalse(cCar.cancelSeat(3));
        // cancel the 1st and 2nd (were both booked)
        assertTrue(cCar.cancelSeat(1));
        assertTrue(cCar.cancelSeat(2));
       
    }      
}
