import java.io.IOException;

/**
 * 
 * @author rafal
 *
 */
public class Driver
{

    public static void main(String[] args) throws IOException
    {
    
        final int YEAR = 2018;
        final int MONTH = 8;
        final int DAY = 30;
        final int HOUR = 17;
        final int MINUTE = 45;

        final String directory = "data";

        MapData mapData = new MapData(YEAR, MONTH, DAY, HOUR, MINUTE, directory);

        mapData.parseFile();
        System.out.println(mapData);
       
    }

}
