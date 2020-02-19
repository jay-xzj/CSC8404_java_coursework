package uk.co.ncl.aj.coursework.car;

import org.junit.Before;
import uk.co.ncl.aj.coursework.fleet.Fleet;
import uk.co.ncl.aj.coursework.license.DrivingLicense;
import uk.co.ncl.aj.coursework.person.Person;
import uk.co.ncl.aj.coursework.license.LicenseNumber;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author JayXu
 * @description:
 * @date 2019/10/25 08:17
 */
public class SmallCarTest {
    private Fleet fleet;
    @Before
    public void setUp(){
        fleet = Fleet.newInstance();
        fleet.initiate();
    }

    @Test
    public void isTankFull() {
    }

    @Test
    public void currentFuel() {
    }

    @Test
    public void getRegistNum() {
    }

    /**
     * @Description: SmallCarTest test addFuelMethod
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void addFuel() throws ParseException {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        fleet.issueCar(person,license, CarFactory.SMALL_CAR);
        Car car = fleet.RECORDS.get(person.getLicense().getLicenseNumber().getNumber());
        int drive = car.drive(50);
        int i = car.addFuel(3);
        Assert.assertEquals(2,i);
    }

    /**
     * @Description: SmallCarTest test getCapacity method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getCapacity() throws ParseException {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        fleet.issueCar(person,license, CarFactory.SMALL_CAR);
        Car car = fleet.RECORDS.get(person.getLicense().getLicenseNumber().getNumber());
        int capacity = car.getCapacity();
        Assert.assertEquals(capacity,49);
    }

    /**
     * @Description: SmallCarTest test drive method
     * @param
     * @return void
     * @throws
     */
    @Test
    public void drive() throws ParseException {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        fleet.issueCar(person,license, CarFactory.SMALL_CAR);
        Car car = fleet.RECORDS.get(person.getLicense().getLicenseNumber().getNumber());
        int drive = car.drive(50);
        Assert.assertEquals(drive,2);
    }
}