package uk.co.ncl.aj.coursework.license;

import org.junit.*;
import uk.co.ncl.aj.coursework.person.Person;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @description: Test LicenseNumber Class
 * @author JayXu
 * @date 2019/10/25 02:32
 */
public class LicenseNumberTest {
    private final LicenseNumber fx = new LicenseNumber("FX", 1900, 101);

    /**
     * @Description: Test LicenseNumber's constructor
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void testConstructor(){
        String license = fx.getNumber();
        Assert.assertEquals(license,"FX-1900-101");
    }

    /**
     * @Description: Test LicenseNumber's constructor
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getPrefix() {
        String prefix = fx.getPrefix();
        Assert.assertEquals(prefix,"FX");
    }

    /**
     * @Description: Test LicenseNumber's constructor
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getMid() {
        int mid = fx.getMid();
        Assert.assertEquals(mid,1900);
    }

    /**
     * @Description: Test LicenseNumber's getSuffix method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getSuffix() {
        int suffix = fx.getSuffix();
        Assert.assertEquals(suffix,101);
    }

    /**
     * @Description: Test LicenseNumber's getNumber method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getNumber() {
        String license = fx.getNumber();
        Assert.assertEquals(license,"FX-1900-101");
    }

    /**
     * @Description: Test LicenseNumber's getUniqueLicense method
     * @param []
     * @return void
     * @throws
     */
    @Test
    public void getUniqueLicense() {
        Calendar ca = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        ca.set(1990,0, 8);
        Date birth = ca.getTime();
        LicenseNumber jx = new LicenseNumber("JX", 1990, 100);
        DrivingLicense drivingLicense = new DrivingLicense(jx, birth, true);
        Person person = new Person("Jay", "Xu", birth, drivingLicense);
        LicenseNumber.LICENSE_NUMBERS.add(jx);
        LicenseNumber uniqueLicense = LicenseNumber.getUniqueLicense(person, birth);
        Assert.assertNotEquals(jx,uniqueLicense);
    }
}