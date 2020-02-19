package uk.co.ncl.aj.coursework.license;


import java.util.Date;

/**
 * @Description: DrivingLicense class
 * @author JayXu
 * @date 2019/10/23 18:16
 */
public class DrivingLicense {
    private final LicenseNumber licenseNumber;//The licence number has three components.  MS-1990-10
    private final Date dateOfIssue;
    private final boolean isFull;//is full driving licence or not

    public DrivingLicense(LicenseNumber licenseNumber, Date dateOfIssue, boolean isFull) {
        this.licenseNumber = licenseNumber;
        this.dateOfIssue = dateOfIssue;
        this.isFull = isFull;
    }

    public LicenseNumber getLicenseNumber() {
        return licenseNumber;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public boolean isFull() {
        return isFull;
    }
}
