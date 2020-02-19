package uk.co.ncl.aj.coursework.car;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.ncl.aj.coursework.fleet.Fleet;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author JayXu
 * @description: Test LargeCar
 * @date 2019/10/25 08:11
 */
public class LargeCarTest {
    private Fleet fleet;
    @Before
    public void setUp(){
        fleet = Fleet.newInstance();
        fleet.initiate();
    }
    /**
     * @Description: LargeCarTest test isTankFull method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void isTankFull() {
        Car ng57_hxe = CarFactory.getInstance(CarFactory.LARGE_CAR, "NG57 HXE");
        Assert.assertTrue(ng57_hxe.isTankFull());
    }

    /**
     * @Description: test LargeCar's currentFuel method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void currentFuel() {
        Car ng57_hxe = CarFactory.getInstance(CarFactory.LARGE_CAR, "NG57 HXE");
        int expResult = 60;
        int result = ng57_hxe.currentFuel();
        assertEquals(expResult,result);
    }

    /**
     * @Description: test LargeCar's getRegistNum method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getRegistNum() {
        Car ng57_hxe = CarFactory.getInstance(CarFactory.LARGE_CAR, "NG57 HXE");
        String registNum = ng57_hxe.getRegistNum();
        Assert.assertEquals("NG57 HXE",registNum);
    }

    /**
     * @Description: Test LargeCar's addFuel method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void addFuel() {
        Map<String, Car> largeCars = fleet.getLargeCars();
        Car value = largeCars.entrySet().iterator().next().getValue();
        int i = value.addFuel(20);
        int ev = 0;
        Assert.assertEquals(ev,i);
    }


    /**
     * @Description: Test LargeCar's drive method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void drive() {
        Map<String, Car> largeCars = fleet.getLargeCars();
        Car value = largeCars.entrySet().iterator().next().getValue();
        value.drive(65);
        int i = value.addFuel(10);
        Assert.assertEquals(i,6);
    }

    /**
     * @Description: Test LargeCar's getCapacity method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getCapacity() {
        Car ng57_hxe = CarFactory.getInstance(CarFactory.LARGE_CAR, "NG57 HXE");
        int capacity = ng57_hxe.getCapacity();
        Assert.assertEquals(capacity,60);
    }
}