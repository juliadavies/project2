import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import junit.framework.Assert;
/**
 * This class tests the MapData class.
 * @author Julia Davies
 * @version 2018-10-03
 * Lab 14
 */
public class MapDataTest
{
    /**
     * Tests the createFileName method
     */
    @Test
    public final void testCreateFileName()
    {
        String fileName = "";
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        fileName = "data" + File.separator + String.format("%04d%02d%02d%02d%02d.mdf", 2018, 8, 30, 17, 45);
        Assert.assertTrue(fileName.equals(test.createFileName()));
    }
    /**
     * Tests the sradAverage getter
     * @throws IOException
     */
    @Test
    public final void testGetSradAverage() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Assert.assertEquals(828.1, test.getSradAverage().getValue(), 0.1);
    }
    /**
     * Tests the sradMax getter
     * @throws IOException
     */
    @Test
    public final void testGetSradMax() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Observation sradMax = new Observation(968, "SLAP");
        Assert.assertTrue(sradMax.getStid().equals(test.getSradMax().getStid()));
        Assert.assertEquals(sradMax.getValue(), test.getSradMax().getValue(), 0.01);
    }
    /**
     * Tests the sradMin getter
     * @throws IOException
     */
    @Test
    public final void testGetSradMin() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Observation sradMin = new Observation(163, "MIAM");
        Assert.assertTrue(sradMin.getStid().equals(test.getSradMin().getStid()));
        Assert.assertEquals(sradMin.getValue(), test.getSradMin().getValue(), 0.01);
    }
    /**
     * Tests the sradTotal getter
     * @throws IOException
     */
    @Test
    public final void testGetSradTotal() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Assert.assertEquals(97715.8, test.getSradTotal().getValue(), 5);
    }
    /**
     * Tests the ta9mAverage getter
     * @throws IOException
     */
    @Test
    public final void testGetTa9mAverage() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Assert.assertEquals(31.6, test.getTa9mAverage().getValue(), 0.1);
    }
    /**
     * Tests the ta9mMax getter
     * @throws IOException
     */
    @Test
    public final void testGetTa9mMax() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Observation ta9mMax = new Observation(34.9, "HOOK");
        Assert.assertTrue(ta9mMax.getStid().equals(test.getTa9mMax().getStid()));
        Assert.assertEquals(ta9mMax.getValue(), test.getTa9mMax().getValue(), 0.01);
    }
    /**
     * Tests the ta9mMin getter
     * @throws IOException
     */
    @Test
    public final void testGetTa9mMin() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Observation ta9mMin = new Observation(20.7, "MIAM");
        Assert.assertTrue(ta9mMin.getStid().equals(test.getTa9mMin().getStid()));
        Assert.assertEquals(ta9mMin.getValue(), test.getTa9mMin().getValue(), 0.01);
    }
    /**
     * Tests the tairAverage getter
     * @throws IOException
     */
    @Test
    public final void testGetTairAverage() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Assert.assertEquals(32.4, test.getTairAverage().getValue(), 0.1);
    }
    /**
     * Tests the tairMax getter
     * @throws IOException
     */
    @Test
    public final void testGetTairMax() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Observation tairMax = new Observation(36.5, "HOOK");
        Assert.assertTrue(tairMax.getStid().equals(test.getTairMax().getStid()));
        Assert.assertEquals(tairMax.getValue(), test.getTairMax().getValue(), 0.01);
    }
    /**
     * Tests the tairMin getter
     * @throws IOException
     */
    @Test
    public final void testGetTairMin() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        Observation tairMin = new Observation(20.8, "MIAM");
        Assert.assertTrue(tairMin.getStid().equals(test.getTairMin().getStid()));
        Assert.assertEquals(tairMin.getValue(), test.getTairMin().getValue(), 0.01);
    }
    /**
     * Tests the MapData toString method
     * @throws IOException
     */
    @Test
    public final void testToString() throws IOException
    {
        MapData test = new MapData(2018, 8, 30, 17, 45, "data");
        test.parseFile();
        String tester = "========================================================\n";
        tester += "=== 2018-08-30 17:45 ===\n";
        tester += "========================================================\n";
        tester += "Maximum Air Temperature[1.5m] = 36.5 C at HOOK\n";
        tester += "Minimum Air Temperature[1.5m] = 20.8 C at MIAM\n";
        tester += "Average Air Temperature[1.5m] = 32.4 C at Mesonet\n";
        tester += "========================================================\n";
        tester += "========================================================\n";
        tester += "Maximum Air Temperature[9.0m] = 34.9 C at HOOK\n";
        tester += "Minimum Air Temperature[9.0m] = 20.7 C at MIAM\n";
        tester += "Average Air Temperature[9.0m] = 31.6 C at Mesonet\n";
        tester += "========================================================\n";
        tester += "========================================================\n";
        tester += "Maximum Solar Radiation[1.5m] = 968.0 W/m^2 at SLAP\n";
        tester += "Minimum Solar Radiation[1.5m] = 163.0 W/m^2 at MIAM\n";
        tester += "Average Solar Radiation[1.5m] = 828.1 W/m^2 at Mesonet\n";
        tester += "========================================================\n";
        Assert.assertTrue(tester.equals(test.toString()));
    }

}
