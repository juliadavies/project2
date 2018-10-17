import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
/**
 * This class tests the Observation class.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */

public class ObservationTest
{
    /**
     * Tests the Observation constructor
     */
    @Test
    public final void testConstructor()
    {
        Observation tester = new Observation(167.4, "Seattle");
        Assert.assertEquals(167.4, tester.getValue(), 0.01);
        Assert.assertTrue("Seattle".equals(tester.getStid()));
    }
    /**
     * Tests the value getter
     */
    @Test
    public final void testGetValue()
    {
        Observation tester = new Observation(345.0, "Tulsa");
        Assert.assertEquals(345.0, tester.getValue(), 0.01);
    }
    /**
     * Tests the isValid method
     */
    @Test
    public final void testIsValid()
    {
        Observation tester = new Observation(15.0, "Tulsa");
        Observation other = new Observation(-983.0, "Tulsa");
        Assert.assertTrue(tester.isValid());
        Assert.assertFalse(other.isValid());
    }
    /**
     * Tests the stid getter
     */
    @Test
    public final void testGetStid()
    {
        Observation tester = new Observation(345.0, "Tulsa");
        Assert.assertTrue("Tulsa".equals(tester.getStid()));
    }
    /**
     * tests the Observation toString
     */
    @Test
    public final void testToString()
    {
        Observation tester = new Observation(15.0, "Tulsa");
        Assert.assertTrue(tester.toString().equals("15.0 at Tulsa\n"));
    }

}
