/**
 * Abstract class which Observation extends. Has an abstract method to 
 * determine whether an Observation is valid.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */
public abstract class AbstractObservation
{
    public AbstractObservation()
    {
        //empty because it is an abstract class.
    }
    protected boolean valid;
    
    abstract boolean isValid();
}
