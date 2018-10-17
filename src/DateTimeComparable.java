import java.util.GregorianCalendar;

/**
 * DateTimeComparable Interface that provides methods for comparing two
 * GregorianCalendar objects.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */
public interface DateTimeComparable
{
    boolean newerThan(GregorianCalendar inDateTimeUTC);
    
    boolean olderThan(GregorianCalendar inDateTimeUTC);
    
    boolean sameAs(GregorianCalendar inDateTimeUTC);
}
