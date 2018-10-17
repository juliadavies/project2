import java.util.GregorianCalendar;
import java.util.Calendar;

/**
 * Statistics class that extends the Observation class and implements the 
 * DateTimeComparable interface. Holds a special type of Observation that
 * has, in addition to a value and stid, variables holding the date, the 
 * number of valid stations in the statistic, and the StatsType of the statistic.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */

public class Statistics extends Observation implements DateTimeComparable
{
    protected final String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss z";
    
    private GregorianCalendar utcDateTime;
    
    private int numberOfReportingStations;
    
    private StatsType statType;
    
    /**
     * A Statistics constructor that sets the values given as parameters
     * @param value the double value of the statistic
     * @param stid the string stid of the statistic
     * @param dateTimeStr A string that should follow the format of DATE_TIME_FORMAT
     * @param numberOfValidStations the int number of valid stations used in calculating the statistic
     * @param inStatType a StatsType, either MAXIMUM, MINIMUM, TOTAL, or AVERAGE.
     */
    public Statistics(double value, String stid, String dateTimeStr, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        utcDateTime = createDateFromString(dateTimeStr);
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;
    }
    /**
     * A Statistics constructor very similar to the other constructor except it 
     * takes in the date as a GregorianCalendar instead of as a string.
     * @param value the double value of the statistic
     * @param stid the String stid of the statistic
     * @param dateTime A string that should follow the format of DATE_TIME_FORMAT
     * @param numberOfValidStations the int number of valid stations used in calculating the statistic
     * @param inStatType a StatsType, either MAXIMUM, MINIMUM, TOTAL, or AVERAGE.
     */
    public Statistics(double value, String stid, GregorianCalendar dateTime, int numberOfValidStations, StatsType inStatType)
    {
        super(value, stid);
        utcDateTime = dateTime;
        numberOfReportingStations = numberOfValidStations;
        statType = inStatType;
    }
    /**
     * Creates a GregorianCalendar object from a String of the format DATE_TIME_FORMAT.
     * @param dateTimeStr String of format DATE_TIME_FORMAT
     * @return the created GregorianCalendar Object
     */
    public GregorianCalendar createDateFromString(String dateTimeStr)
    {
        GregorianCalendar result;
        int year = Integer.parseInt(dateTimeStr.substring(0, 4));
        int month = Integer.parseInt(dateTimeStr.substring(5, 7));
        int day = Integer.parseInt(dateTimeStr.substring(8, 10));
        int hour = Integer.parseInt(dateTimeStr.substring(13, 15));
        int minute = Integer.parseInt(dateTimeStr.substring(16, 18));
        int second = Integer.parseInt(dateTimeStr.substring(19, 21));
        result = new GregorianCalendar(year, month, day, hour, minute, second);
        return result;
    }
    /**
     * Creates a string of the format DATE_TIME_FORMAT from a GregorianCalendar object
     * @param calendar a GregorianCalendar object to be turned into a string
     * @return the created string of format DATE_TIME_FORMAT
     */
    public String createStringFromDate(GregorianCalendar calendar)
    {
        String result = "";
        result += String.format("%04d-%02d-%02d'T'%02d:%02d:%02d z", calendar.get(Calendar.YEAR), 
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
        return result;
    }
    /**
     * getter for numberOfReportingStations
     * @return numberOfReportingStations
     */
    public int getNumberOfReportingStations()
    {
        return numberOfReportingStations;
    }
    /**
     * getter for the utcDateTime string
     * @return utcDateTime string of format DATE_TIME_FORMAT
     */
    public String getUTCDateTimeString()
    {
        return createStringFromDate(utcDateTime);
    }
    /**
     * Compares two GregorianCalendars and returns true if the utcDateTime of the calling Statistics Object
     * has a newer date than the GregorianCalendar object passed as a parameter.
     */
    public boolean newerThan(GregorianCalendar inDateTime)
    {
        boolean isNewer = false;
        if (!this.utcDateTime.before(inDateTime))
        {
            isNewer = true;
        }
        return isNewer;
    }
    /**
     * Compares two GregorianCalendars and returns true if the utcDateTime of the calling Statistics Object
     * has an older date than the GregorianCalendar object passed as a parameter.
     */
    public boolean olderThan(GregorianCalendar inDateTime)
    {
        boolean isOlder = false;
        if (this.utcDateTime.before(inDateTime))
        {
           isOlder = true;
        }
        return isOlder;
    }
    /**
     * Compares two GregorianCalendars and returns true if the utcDateTime of the calling Statistics Object
     * has the same date as the GregorianCalendar object passed as a parameter.
     */
    public boolean sameAs(GregorianCalendar inDateTime)
    {
        boolean isSame = false;
        if (this.utcDateTime.compareTo(inDateTime) == 0)
        {
            isSame = true;
        }
        return isSame;
    }
    /**
     * A simple toString that prints the value and stid in the format
     * "%.1f at %s\n" where f is the value and s is the stid
     */
    @Override
    public String toString()
    {
        String output = "";
        if(statType.equals(StatsType.MAXIMUM))
        {
            output = "Maximum:";
        }
        else if(statType.equals(StatsType.MINIMUM))
        {
            output = "Minimum:";
        }
        else
        {
            output = "Average:";
        }
        output = String.format("%.1f at %s\n", this.value, this.stid);
        return output;
    }
}
