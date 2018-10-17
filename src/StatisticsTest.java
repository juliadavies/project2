import static org.junit.Assert.*;

import java.util.GregorianCalendar;

import org.junit.Test;

import junit.framework.Assert;

/**
 * This class tests the Statistics class.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */

public class StatisticsTest
{
    /**
     * Tests the Statistics constructor which takes in the date as a 
     * string instead of as a GregorianCalendar object.
     */
    @Test
    public final void testStatisticsDoubleStringStringIntStatsType()
    {
        Statistics tester = new Statistics(100.0, "Bunny", "2018-10-30'T'12:15:49 z", 3, StatsType.MAXIMUM);
        Assert.assertEquals(100.0, tester.getValue(), 100);
        Assert.assertTrue("Bunny".equals(tester.getStid()));
        Assert.assertTrue("2018-10-30'T'12:15:49 z".equals(tester.getUTCDateTimeString()));
    }
    /**
     * Tests the Statistics constructor which takes in the date as a 
     * GregorianCalendar object instead of as a string.
     */
    @Test
    public final void testStatisticsDoubleStringGregorianCalendarIntStatsType()
    {
        GregorianCalendar test = new GregorianCalendar(2018, 10, 30, 12, 15, 49);
        Statistics tester = new Statistics(100.0, "Bunny", test, 3, StatsType.MAXIMUM);
        Assert.assertEquals(100.0, tester.getValue(), 100);
        Assert.assertTrue("Bunny".equals(tester.getStid()));
        Assert.assertTrue("2018-10-30'T'12:15:49 z".equals(tester.getUTCDateTimeString()));
    }
    /**
     * Tests the createDateFromString method
     */
    @Test
    public final void testCreateDateFromString()
    {
        String testString = "2018-08-30'T'17:45:00 z";
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        GregorianCalendar testCalendar = new GregorianCalendar(2018, 8, 30, 17, 45);
        Assert.assertTrue(testCalendar.equals(test.createDateFromString(testString)));
    }
    /**
     * Tests the createStringFromDate method
     */
    @Test
    public final void testCreateStringFromDate()
    {
        GregorianCalendar tester = new GregorianCalendar(2018, 8, 30, 17, 45, 0);
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Assert.assertTrue(test.createStringFromDate(tester).equals(test.getUTCDateTimeString()));
    }
    /**
     * Tests the getNumberOfReportingStations method
     */
    @Test
    public final void testGetNumberOfReportingStations()
    {
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Assert.assertEquals(89, test.getNumberOfReportingStations());
    }
    /**
     * Tests the getUTCDateTimeString method
     */
    @Test
    public final void testGetUTCDateTimeString()
    {
        String dateString = "2018-08-30'T'17:45:00 z";
        Statistics test = new Statistics(345.3, "TULSA", dateString, 89, StatsType.AVERAGE);
        Assert.assertTrue(test.getUTCDateTimeString().equals(dateString));
                                                              
    }
    /**
     * Tests the newerThan method
     */
    @Test
    public final void testNewerThan()
    {
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Statistics otherTest = new Statistics(345.3, "TULSA", "2017-08-29'T'17:45:00 z", 89, StatsType.AVERAGE);
        Assert.assertTrue(test.newerThan(otherTest.createDateFromString(otherTest.getUTCDateTimeString())));
        Assert.assertFalse(otherTest.newerThan(test.createDateFromString(test.getUTCDateTimeString())));
    }
    /**
     * Tests the olderThan method
     */
    @Test
    public final void testOlderThan()
    {
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Statistics otherTest = new Statistics(345.3, "TULSA", "2018-08-29'T'17:45:00 z", 89, StatsType.AVERAGE);
        Assert.assertFalse(test.olderThan(otherTest.createDateFromString(otherTest.getUTCDateTimeString())));
        Assert.assertTrue(otherTest.olderThan(test.createDateFromString(test.getUTCDateTimeString())));
    }
    /**
     * Tests the sameAs method
     */
    @Test
    public final void testSameAs()
    {
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Statistics otherTest = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Statistics thirdTest = new Statistics(345.3, "TULSA", "2017-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Assert.assertTrue(test.sameAs(otherTest.createDateFromString(otherTest.getUTCDateTimeString())));
        Assert.assertFalse(test.sameAs(thirdTest.createDateFromString(thirdTest.getUTCDateTimeString())));
    }
    /**
     * Tests the Statistics toString method
     */
    @Test
    public final void testToString()
    {
        Statistics test = new Statistics(345.3, "TULSA", "2018-08-30'T'17:45:00 z", 89, StatsType.AVERAGE);
        Assert.assertTrue(test.toString().equals("345.3 at TULSA\n"));
    }

}
