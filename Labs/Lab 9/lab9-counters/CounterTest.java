/**
 * The test class CounterTest.
 *
 * @author  (Varrahan Uthayan)
 * @version (March 08 2023)
 */
public class CounterTest extends junit.framework.TestCase
{
    private Counter c1, c2;
    
    /**
     * Default constructor for test class CounterTest
     */
    public CounterTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    protected void setUp()
    {
        c1 = new RollOverCounter(1, 10);
        c2 = new LimitedCounter(1, 10);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    protected void tearDown()
    {
    }
    
    /**
     * Tests the original RollOverCounter methods.
     */
    public void testAllRollOverCounterMethods()
    {    
        /* Verify that the counter is in the correct initial state. */
        assertEquals(1, c1.minimumCount());
        assertEquals(10, c1.maximumCount());
        assertEquals(1, c1.count());        
        
        assertTrue(c1.isAtMinimum());
        assertFalse(c1.isAtMaximum());
  
        /* Count 1 -> 2 */
        
        c1.countUp();
        assertEquals(2, c1.count());
        assertFalse(c1.isAtMinimum());
        
        /* Count 3, 4, ...9, 10 */
        for (int i = 1; i < 9; i++) {
            c1.countUp();
        }
        assertTrue(c1.isAtMaximum());
        
        /* Count 10 -> 1 */
        c1.countUp();
        assertEquals(1, c1.count());
        
        /* Verify that reset works. */
        c1.countUp();
        c1.countUp();
        c1.reset();
        assertEquals(1, c1.count());
    }  
    /**
     * Tests the lab 9 LimitedCounter methods
     */
    public void testAllLimitedCounterMethods(){
        assertEquals(1, c2.minimumCount());
        assertEquals(10, c2.maximumCount());
        assertEquals(1, c2.count());  
        
        assertTrue(c2.isAtMinimum());
        assertFalse(c2.isAtMaximum());
  
        /* Count 1 -> 2 */
        
        c2.countUp();
        assertEquals(2, c2.count());
        assertFalse(c2.isAtMinimum());
        
        /* Count 3, 4, ...9, 10 */
        for (int i = 1; i < 9; i++) {
            c2.countUp();
        }
        assertTrue(c2.isAtMaximum());
        
        /* Count 10 -> 10 as it stays on max */
        c2.countUp();
        assertEquals(10, c2.count());
        
        /* Verify that reset works. */
        c2.countUp();
        c2.countUp();
        c2.reset();
        assertEquals(1, c2.count());
    }
    /**
     * Tests RollOverCounter's new methods 
     */
    public void testNewRollOverCounterMethods(){
        c1.setToMaximum();
        assertEquals(10,c1.count());
        
        
        for (int i = 1; i < 10; i++) {
            c1.countDown();
        }
        assertTrue(c1.isAtMinimum());
        
        c1.countDown();
        c1.countDown();
        assertEquals(9,c1.count());
        
        
        
    }
    /**
     * Tests LimitedCounter's new methods
     */
    public void testNewLimitedCounterMethods(){
        c2.setToMaximum();
        assertEquals(10,c2.count());
        
        for (int i = 1; i < 10; i++) {
            c2.countDown();
        }
        assertTrue(c2.isAtMinimum());
        
        c2.reset();
        assertEquals(1, c2.count());
        
        c2.countDown();
        c2.countDown();
        assertEquals(1, c2.count());
        
        
    }
}
