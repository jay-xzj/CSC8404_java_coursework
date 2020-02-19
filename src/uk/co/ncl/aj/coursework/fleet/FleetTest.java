package uk.co.ncl.aj.coursework.fleet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.ncl.aj.coursework.license.DrivingLicense;
import uk.co.ncl.aj.coursework.person.Person;
import uk.co.ncl.aj.coursework.car.Car;
import uk.co.ncl.aj.coursework.car.CarFactory;
import uk.co.ncl.aj.coursework.license.LicenseNumber;

import java.text.ParseException;
import java.util.*;

/**
 * @description: Fleet test class
 * @author JayXu
 * @date 2019/10/25 11:55
 */
public class FleetTest {
    private Fleet fleet;
    @Before
    public void setUp(){
        fleet = Fleet.newInstance();
    }

    /**
     * Test availableCars method in Fleet
     */
    @Test
    public void availableCars() {
        Map<String, Car> small = fleet.availableCars("small");
        Assert.assertEquals(small.size(),20);
        Map<String, Car> large = fleet.availableCars("large");
        Assert.assertEquals(large.size(),10);
    }

    /**
     * Test getRentedCars method in Fleet
     */
    @Test
    public void getRentedCars() {
        List<Car> rentedCars = fleet.getRentedCars();
        Assert.assertEquals(rentedCars.size(),0);
    }

    /**
     * Test getCar method in Fleet
     */
    @Test
    public void getCar() throws ParseException {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        fleet.issueCar(person,license, CarFactory.SMALL_CAR);
        Car car = fleet.getCar(person);
        Assert.assertNotNull(car);
    }

    /**
     * Test issueCar method in Fleet
     */
    @Test
    public void issueCar() throws ParseException {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        fleet.issueCar(person,license, CarFactory.SMALL_CAR);
    }

    /**
     * Test terminateRental method in Fleet
     */
    @Test
    public void terminateRental() throws ParseException {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        fleet.issueCar(person,license, CarFactory.SMALL_CAR);
        Car car = fleet.getCar(person);
        fleet.terminateRental(person);
    }
}