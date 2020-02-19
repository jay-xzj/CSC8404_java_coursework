package uk.co.ncl.aj.coursework.license;

import org.junit.Assert;
import org.junit.Test;
import uk.co.ncl.aj.coursework.person.Person;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author JayXu
 * @description:
 * @date 2019/10/25 02:58
 */
public class DrivingLicenseTest {

    /**
     * @Description: Test DrivingLicense's getLicenseNumber method.
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getLicenseNumber() {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        LicenseNumber licenseNumber = license.getLicenseNumber();
        Assert.assertNotNull(licenseNumber);
    }

    /**
     * @Description: Test DrivingLicense's getLicenseNumber method.
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getDateOfIssue() {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        Date dateOfIssue = license.getDateOfIssue();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String format = simpleDateFormat.format(birth);
        Assert.assertEquals("1990-01-08",format);
    }

    /**
     * @Description: Test DrivingLicense's isFull method.
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void isFull() {
        Calendar ca =  Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        DrivingLicense license = new DrivingLicense(new LicenseNumber("JX", 1990, 100), birth, true);
        Person person = new Person("Xu", "Jay", birth,license);
        boolean full = license.isFull();
        Assert.assertTrue(full);
    }
}