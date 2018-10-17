/**
 * Observation class that holds an observation with a double value and stid String.
 * Also is marked as invalid if the value is less than or equal to -900.
 * Copied from Lab 1 Observation class with minor modifications
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */
public class Observation extends AbstractObservation{
    
    protected double value;
    
    protected String stid;
    
    /**
     * Observation constructor
     * @param value the double value of the observation
     * @param stid the string representing the location of the observation
     */
    public Observation(double value, String stid)
    {
        this.value = value;
        this.stid = stid;
    }
    /**
     * Value getter
     * @return the double value of the observation
     */
    public double getValue()
    {
        return value;
    }
    /**
     * Checks whether the value of the observation is above -900 or not, and returns
     * true if it is
     * @return valid boolean
     */
    public boolean isValid()
    {
        boolean valid = true;
        if (value <= -900)
        {
            valid = false;
        }
        return valid;
    }
    /**
     * stid getter
     * @return the stid of the observation
     */
    public String getStid()
    {
        return stid;
    }
    /**
     * Simple toString that prints out the observation's value and stid in the format
     * <value at stid> with value truncated to one decimal place
     */
    @Override
    public String toString()
    {
        String output = "";
        output = String.format("%.1f at %s\n", this.value, this.stid);
        return output;
    }
}