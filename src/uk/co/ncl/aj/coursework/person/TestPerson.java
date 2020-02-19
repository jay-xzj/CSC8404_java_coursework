package uk.co.ncl.aj.coursework.person;

import uk.co.ncl.aj.coursework.license.DrivingLicense;
import uk.co.ncl.aj.coursework.license.LicenseNumber;
import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author JayXu
 * @description: Test person
 * @date 2019/10/25 00:38
 */
public class TestPerson {
    private final Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

    /**
     * @Description: Test Person's getForename method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getForenameTest(){
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        //System.out.println(person.getForename());
        assertEquals(person.getForename(),"Jay");
    }

    /**
     * @Description: Test Person's getSurname method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getSurnameTest(){
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth, license);
        //System.out.println(person.getForename());
        assertEquals(person.getSurname(),"Xu");
    }

    /**
     * @Description: Test Person's getDateOfBirth method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getDateOfBirthTest(){
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth, license);
        //System.out.println(person.getForename());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Assert.assertEquals(simpleDateFormat.format(person.getDateOfBirth()),"1990-01-08");
    }

    /**
     * @Description: Test Person's getFullName method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getFullNameTest(){
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth, license);
        String fullName = person.getFullName();
        Assert.assertEquals(fullName,"Jay Xu");
    }

    /**
     * @Description: Test Person's constructor
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void constructorTest(){
        //ca.set(1990,0, 8);
        ca.set(1890,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        try{
            new Person ("", "Xu",birth, license);
            fail("Surname can't be empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid value, surname can't be empty!",e.getMessage());
        }

        try {
            new Person ("Jay", "",birth, license);
            fail("Forename can't be empty.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid value, forename can't be empty!",e.getMessage());
        }

        try {
            new Person ("Jay", "Xu",birth, license);
            fail("Invalid format of date of birth!");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid format of date of birth!",e.getMessage());
        }
    }
}
