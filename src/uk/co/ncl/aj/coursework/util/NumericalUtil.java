package uk.co.ncl.aj.coursework.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author JayXu
 * @description: a tool to calculate numeral
 * @date 2019/10/24 0024 22:32
 */
public class NumericalUtil {
    /***
     * @Description: Using date of birth to get age of the person.
     * @param dateOfBirth age string
     * @return int age
     * @throws ParseException
     */
    public static int getIntervalYears(Date dateOfBirth) throws ParseException {
        Calendar cal = Calendar.getInstance();
        if (cal.before(dateOfBirth)) {
            throw new IllegalArgumentException("Date of birth is later than now!");
        }
        //current year
        int yearNow = cal.get(Calendar.YEAR);
        //current month
        int monthNow = cal.get(Calendar.MONTH);
        //current date
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(dateOfBirth);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        //age
        int age = yearNow - yearBirth;
        //adjust the age by distinguish month and day of month
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth)
                    //current date is before the date of birth
                    age--;
            }else{
                //current month is before the month of birth
                age--;
            }
        }
        return age;
    }

    /**
     * @Description: method to generate registration number
     * @param
     * @return java.lang.String registration number
     * @throws
     * @author JayXu
     * @date 2019/10/25 10:37
     */
    public static String generatePlate(){
        StringBuilder sb = new StringBuilder();
        int max = 90;
        int min = 65;
        int numMax = 9;
        int numMin = 0;
        for (int i = 0; i < 8; i++) {
            if (i==2||i==3){
                int n = (int) (Math.random() * (numMax - numMin) + numMin);
                sb.append(n);
            }else if (i==4){
                sb.append(" ");
            }else{
                char v = (char) (Math.random() * (max - min) + min);
                sb.append(v);
            }
        }
        String plate = sb.toString();
        return plate;
    }

}
