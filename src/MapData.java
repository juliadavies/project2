import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * MapData class that stores arrays of Observations and calculates statistics from the data
 * and prints them in a special format. If there are too many invalid observations,
 * an error message is printed instead.
 * Copied from Lab1 MapData class with modifications.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */
public class MapData {
    
    private final int NUMBER_OF_MISSING_OBSERVATIONS = 10;
    
    private final String TA9M = "TA9M";
    
    private final String TAIR = "TAIR";
    
    private final String SRAD = "SRAD";
    
    private final String STID = "STID";
    
    private int stidPosition = -1;
    
    private int tairPosition = -1;
    
    private int sradPosition = -1;
    
    private int ta9mPosition = -1;
    
    private final String MESONET = "Mesonet";
    
    private Statistics tairMin;
    
    private Statistics tairMax;
    
    private Statistics tairAverage;
    
    private Statistics ta9mMin;
    
    private Statistics ta9mMax;
    
    private Statistics ta9mAverage;
    
    private Statistics sradMin;
    
    private Statistics sradMax;
    
    private Statistics sradAverage;
    
    private Statistics sradTotal;
    
    private String fileName;
    
    private GregorianCalendar utcDateTime;
    
    private boolean canCalculate = true;
    
    /**
     * The MapData constructor. Uses the parameters to set fileName in the format 
     * "directory/yearmonthdayhourminute.mdf".
     * Also creates the utcDateTime object using the parameters.
     * 
     */
    public MapData(int year, int month, int day, int hour, int minute, String directory)
    {
        fileName = directory + File.separator;
        fileName += String.format("%04d%02d%02d%02d%02d.mdf", year, month, day, hour, minute);
        utcDateTime = new GregorianCalendar(year, month, day, hour, minute);
    }
    
    /**
     * Creates a file name from the input date information in the format
     * "%s/%d%02d%02d%02d%02d.mdf", directory, year, month, day, hour, minute
     * @return fileName
     */
    public String createFileName()
    {
        
        return fileName;
    }
    /**
     * Parses the header to find which columns hold each type of data, and sets the ta9mPosition, tairPosition,
     * sradPosition, and stidPosition to the correct values for their respective columns' indices.
     * @param inParamStr
     */
    private void parseParamHeader(String inParamStr)
    {
        String[] Header = inParamStr.split("\\s+");
        for (int i = 0; i < Header.length; ++i)
        {
            if (Header[i].equals(TA9M))
            {
                ta9mPosition = i;
            }
            if (Header[i].equals(TAIR))
            {
                tairPosition = i;
            }
            if (Header[i].equals(SRAD))
            {
                sradPosition = i;
            }
            if (Header[i].equals(STID))
            {
                stidPosition = i;
            }
        }
    }
    /**
     * Reads a file and separates the data at spaces, calculating statistics for solar
     * radiation, air temperature, and taken at 9 meters data. Invalid observations are
     * counted so that the data set can be thrown out if there are more than the specified
     * number.
     * @throws IOException
     */
    public void parseFile() throws IOException
    {
        ArrayList<Observation> sradData = new ArrayList<Observation>();
        ArrayList<Observation> tairData = new ArrayList<Observation>();
        ArrayList<Observation> ta9mData = new ArrayList<Observation>();
        
        int numberOfStations = 0;
        int invalidCount = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(this.createFileName()));
        //header
        String lineOfData = br.readLine();
        //header line 2
        lineOfData = br.readLine();
        //the header line that must be parsed to set the column position indices
        lineOfData = br.readLine();
        parseParamHeader(lineOfData);
        //first line of data
        lineOfData = br.readLine();
        //loops while there is still data remaining
        while (lineOfData != null)
        {
         //splits the line at spaces
        String[] dataLine = lineOfData.split("\\s+");
        //fills the respective Observation arrays with the data
        sradData.add(new Observation(Double.valueOf(dataLine[sradPosition]), dataLine[stidPosition]));
        tairData.add(new Observation(Double.valueOf(dataLine[tairPosition]), dataLine[stidPosition]));
        ta9mData.add(new Observation(Double.valueOf(dataLine[ta9mPosition]), dataLine[stidPosition]));
        if (!sradData.get(numberOfStations).isValid())
        {
            ++invalidCount;
        }
        if (!tairData.get(numberOfStations).isValid())
        {
            ++invalidCount;
        }
        if (!ta9mData.get(numberOfStations).isValid())
        {
            ++invalidCount;
        }
        numberOfStations += 1;
        lineOfData = br.readLine();
        }
        br.close();
        
        if (invalidCount <= NUMBER_OF_MISSING_OBSERVATIONS)
        {
            calculateStatistics(sradData, SRAD);
            calculateStatistics(ta9mData, TA9M);
            calculateStatistics(tairData, TAIR);
        }
        else 
        {
            canCalculate = false;
        }
    }
    /**
     * Calculates the minimum, maximum, and average values from the input data. This data is stored in the correct
     * variables based on the paramId which decides whether it is srad, tair, or ta9m data.
     * @param inData an ArrayList that refers to either sradData, ta9mData, or tairData
     * @param paramId should be SRAD, TA9M, or TAIR
     */
    private void calculateStatistics(ArrayList<Observation> inData, String paramId)
    {
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;
        double avgValue = 0;
        double totalValue = 0;
        int validCount = 0;
        int minIndex = 0;
        int maxIndex = 0;
        for (int i = 0; i < inData.size(); ++i)
        {
            //we do not want to include invalid data in our calculations
            if (inData.get(i).isValid())
            {
                if (inData.get(i).getValue() < minValue)
                {
                    minValue = inData.get(i).getValue();
                    minIndex = i;
                }
                if (inData.get(i).getValue() > maxValue)
                {
                    maxValue = inData.get(i).getValue();
                    maxIndex = i;
                }
                totalValue += inData.get(i).getValue();
                ++validCount;
            }
        }
            avgValue = totalValue / validCount;
        StatsType max = StatsType.MAXIMUM;
        StatsType min = StatsType.MINIMUM;
        StatsType avg = StatsType.AVERAGE;
        StatsType total = StatsType.TOTAL;
        /*
         * checks paramId so the stats can be stored in the proper place based on whether the calculated stats
         * are from srad data, tair data, or ta9m data.
         */
        if (paramId.equals(SRAD))
        {
            sradMax = new Statistics(maxValue, inData.get(maxIndex).getStid(), utcDateTime, validCount, max);
            sradMin = new Statistics(minValue, inData.get(minIndex).getStid(), utcDateTime, validCount, min);
            sradAverage = new Statistics(avgValue, MESONET, utcDateTime, validCount, avg);
            sradTotal = new Statistics(totalValue, MESONET, utcDateTime, validCount, total);
        }
        else if (paramId.equals(TAIR))
        {
            tairMax = new Statistics(maxValue, inData.get(maxIndex).getStid(), utcDateTime, validCount, max);
            tairMin = new Statistics(minValue, inData.get(minIndex).getStid(), utcDateTime, validCount, min);
            tairAverage = new Statistics(avgValue, MESONET, utcDateTime, validCount, avg);
        }
        else
        {
            ta9mMax = new Statistics(maxValue, inData.get(maxIndex).getStid(), utcDateTime, validCount, max);
            ta9mMin = new Statistics(minValue, inData.get(minIndex).getStid(), utcDateTime, validCount, min);
            ta9mAverage = new Statistics(avgValue, MESONET, utcDateTime, validCount, avg);
        }
    }
    /**
     * sradAverage getter
     * @return sradAverage
     */
    public Observation getSradAverage()
    {
        return sradAverage;
    }
    /**
     * sradMax getter
     * @return sradMax
     */
    public Observation getSradMax()
    {
        return sradMax;
    }
    /**
     * sradMin getter
     * @return sradMin
     */
    public Observation getSradMin()
    {
        return sradMin;
    }
    /**
     * sradTotal getter
     * @return sradTotal
     */
    public Observation getSradTotal()
    {
        return sradTotal;
    }
    /**
     * ta9mAverage getter
     * @return ta9mAverage
     */
    public Observation getTa9mAverage()
    {
        return ta9mAverage;
    }
    /**
     * ta9mMax getter
     * @return ta9mMax
     */
    public Observation getTa9mMax()
    {
        return ta9mMax;
    }
    /**
     * ta9mMin getter
     * @return ta9mMin
     */
    public Observation getTa9mMin()
    {
        return ta9mMin;
    }
    /**
     * tairAverage getter
     * @return tairAverage
     */
    public Observation getTairAverage()
    {
        return tairAverage;
    }
    /**
     * tairMax getter
     * @return tairMax
     */
    public Observation getTairMax()
    {
        return tairMax;
    }
    /**
     * tairMin getter
     * @return tairMin
     */
    public Observation getTairMin()
    {
        return tairMin;
    }
    /**
     * toString method that prints the calculated statistics from all of the data in a 
     * special format, or prints that the data is invalid if there were too many invalid
     * observations.
     */
    public String toString()
    {

        String output = "";
        if (canCalculate)
        {
            output += "========================================================\n";
            output += String.format("=== %d-%02d-%02d %02d:%02d ===\n", utcDateTime.get(Calendar.YEAR), utcDateTime.get(Calendar.MONTH), 
                    utcDateTime.get(Calendar.DATE), utcDateTime.get(Calendar.HOUR_OF_DAY), utcDateTime.get(Calendar.MINUTE));
            output += "========================================================\n";
            output += String.format("Maximum Air Temperature[1.5m] = %.1f C at %s\n", tairMax.getValue(), tairMax.getStid());
            output += String.format("Minimum Air Temperature[1.5m] = %.1f C at %s\n", tairMin.getValue(), tairMin.getStid());
            output += String.format("Average Air Temperature[1.5m] = %.1f C at %s\n", tairAverage.getValue(), tairAverage.getStid());
            output += "========================================================\n";
            output += "========================================================\n";
            output += String.format("Maximum Air Temperature[9.0m] = %.1f C at %s\n", ta9mMax.getValue(), ta9mMax.getStid());
            output += String.format("Minimum Air Temperature[9.0m] = %.1f C at %s\n", ta9mMin.getValue(), ta9mMin.getStid());
            output += String.format("Average Air Temperature[9.0m] = %.1f C at %s\n", ta9mAverage.getValue(), ta9mAverage.getStid());
            output += "========================================================\n";
            output += "========================================================\n";
            output += String.format("Maximum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", sradMax.getValue(), sradMax.getStid());
            output += String.format("Minimum Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", sradMin.getValue(), sradMin.getStid());
            output += String.format("Average Solar Radiation[1.5m] = %.1f W/m^2 at %s\n", sradAverage.getValue(), sradAverage.getStid());
            output += "========================================================\n";
        }
        else
        {
            output += "Observation not valid. Cannot calculate statistics.";
        }
        return output;
    }
}