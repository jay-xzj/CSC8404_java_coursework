package uk.co.ncl.aj.coursework.person;

import uk.co.ncl.aj.coursework.license.DrivingLicense;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * @author JayXu
 * @description: Customers of the rental company
 * @date 2019/10/23 0023 17:40
 */
public final class Person {
	private final String surname; // last name
	private final String forename;// first name
	private String fullName;//first name[]last name
	private final Date dateOfBirth;//birthday
	private final DrivingLicense license;//driving license
	
	public Person(String surname, String forename, Date dateOfBirth, DrivingLicense license) {
		//verify the nullability of the member variables
		if (surname.trim().equals("")) {
			throw new IllegalArgumentException("Invalid value, surname can't be empty!");
		}
		if (forename.trim().equals("")){
			throw new IllegalArgumentException("Invalid value, forename can't be empty!");
		}
		String regix = "^(19|20)\\d{2}-(1[0-2]|0?[1-9])-(0?[1-9]|[1-2][0-9]|3[0-1])$";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String format = sdf.format(dateOfBirth);
		if (dateOfBirth==null||!format.matches(regix)){
			throw new IllegalArgumentException("Invalid format of date of birth, please input this way 'yyyy-MM-dd'!");
		}
		this.surname = surname;
		this.forename = forename;
		this.dateOfBirth = dateOfBirth;
		this.license = license;
	}

	public String getSurname() {
		return surname;
	}

	public String getForename() {
		return forename;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public String getFullName(){
		return getForename()+" "+getSurname();
	}

	public DrivingLicense getLicense() {
		return license;
	}

	/**
	 * Two people are considered to be the same if they have the same name and the same date of birth.
	 */
	@Override
	public boolean equals(Object o) {
		// reflexivity
		if (this == o) {
			return true;
		}
		// non-nullity
		if (!(o instanceof Person)) {
			return false;
		}
		// consistency
		Person c = (Person)o;
		//(Objects.equals(forename, c.forename)) equals (surname == null?c.surname == null : surname.equals(c.surname))
		return dateOfBirth.equals(c.dateOfBirth)
				&& ((Objects.equals(surname, c.surname)))
				&& (Objects.equals(forename, c.forename))
				&& (Objects.equals(license,c.license));
	}
	
	@Override
	public int hashCode() {
		int hashcode = 1;
		hashcode = 31 * hashcode
				+ (surname == null?0:surname.hashCode())
				+ (forename == null?0:forename.hashCode())
				+ (dateOfBirth == null?0:dateOfBirth.hashCode())
				+ (license == null?0:license.hashCode());
		return 31*hashcode;
	}

	@Override
	public String toString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String birth = simpleDateFormat.format(getDateOfBirth());
		String number = getLicense().getLicenseNumber().getNumber();
		return "Person: Name["+getFullName()+"] DateOfBirth["+birth+"] DrivingLicense["+number+"]";
	}
}
