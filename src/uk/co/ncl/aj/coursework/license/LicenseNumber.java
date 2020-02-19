package uk.co.ncl.aj.coursework.license;

import uk.co.ncl.aj.coursework.person.Person;

import java.util.*;

/***
 * @Description: License number class to generate an unique license number.
 *
 * You must guarantee the uniqueness of licence numbers.
 *
 * @author JayXu
 * @date 2019/10/24 19:18
 */
public class LicenseNumber {
	static final Set<LicenseNumber> LICENSE_NUMBERS = new HashSet<LicenseNumber>();
	private final String prefix;
	private final int mid;
	private final int suffix;
	private String number;

	public LicenseNumber(String prefix, int mid, int suffix) {
		this.prefix = prefix;
		this.mid = mid;
		this.suffix = suffix;
	}

	public final String getPrefix() {
		return prefix;
	}

	public final int getMid() {
		return mid;
	}

	public final int getSuffix() {
		return suffix;
	}

	public final String getNumber() {
		return getPrefix()+ "-" +getMid()+ "-" +getSuffix();
	}

	/***
	 * @Description: To generate an unique license number.
	 * @param person who owns the license and wants to rent a car.
	 * @param dateOfIssue date of issue of the license
	 * @return license.LicenseNumber unique license number
	 * @throws
	 * @author JayXu
	 * @date 2019/10/25 00:29
	 */
 	static LicenseNumber getUniqueLicense(Person person, Date dateOfIssue){
		//get the prefix
		String fStr = person.getForename().substring(0,1).toUpperCase();
		String sStr = person.getSurname().substring(0, 1).toUpperCase();
		String prefix = sStr.concat(fStr);
		//get the middle string
		Calendar ca = Calendar.getInstance();
		ca.setTime(dateOfIssue);
		int mid = ca.get(Calendar.YEAR);
		//get the suffix
		int suffix = (int) (Math.random() * 1000);
		//combine and add to HashSet
		//String licenseNumber = prefix+"-"+mid+"-"+suffix;
		LicenseNumber licenseNumber = new LicenseNumber(prefix, mid, suffix);
		if (LICENSE_NUMBERS.contains(licenseNumber)){
			getUniqueLicense(person, dateOfIssue);
		}else{
			LICENSE_NUMBERS.add(licenseNumber);
		}
		return licenseNumber;
	}
}
