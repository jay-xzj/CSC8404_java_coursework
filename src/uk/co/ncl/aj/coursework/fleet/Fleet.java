package uk.co.ncl.aj.coursework.fleet;

import uk.co.ncl.aj.coursework.license.DrivingLicense;
import uk.co.ncl.aj.coursework.person.Person;
import uk.co.ncl.aj.coursework.car.Car;
import uk.co.ncl.aj.coursework.car.CarFactory;
import uk.co.ncl.aj.coursework.car.LargeCar;
import uk.co.ncl.aj.coursework.car.SmallCar;
import uk.co.ncl.aj.coursework.util.NumericalUtil;

import java.text.ParseException;
import java.util.*;
/**
 * @description: Fleet to store small cars and large cars.
 * @author JayXu
 * @date 2019/10/23 0023 19:44
 */
public class Fleet {
    private final static Set<String> REGISTRATIONS = new HashSet<String>();//already appeared registration numbers
    public final static Map<String, Car> RECORDS = new HashMap<String,Car>();
    //K:V = licenseNumber : Car
    static Map<String,Car> SMALL_CARS;
    static Map<String,Car> LARGE_CARS;
    //member variables
    private Map<String, Car> smallCars;//a map to initiate all the small cars.
    private Map<String, Car> largeCars;//a map to initiate all the large cars.


    private static Fleet fleet;
    //private constructor
    private Fleet(){ }
    //factory method
    public static Fleet newInstance(){
        if (fleet == null) {
            fleet = new Fleet();
        }
        return fleet;
    }


    /***
     * @Description: Initiate the singleton instance of fleet
     * @param
     * @return void
     * @throws
     * @author JayXu
     * @date 2019/10/25 11:46
     */
    public void initiate(){
        smallCars=new HashMap<String, Car>(20);
        largeCars=new HashMap<String, Car>(10);
        for (int i = 0; i < 20; i++) {
            String registration = uniqueRegist();
            smallCars.put(registration,CarFactory.getInstance(CarFactory.SMALL_CAR,uniqueRegist()));
        }
        for (int i = 0; i < 10; i++) {
            String registration = uniqueRegist();
            largeCars.put(registration,CarFactory.getInstance(CarFactory.LARGE_CAR, uniqueRegist()));
        }
        fleet.setLargeCars(largeCars);
        fleet.setSmallCars(smallCars);
    }

    public Map<String, Car> getSmallCars() {
        return smallCars;
    }

    public void setSmallCars(Map<String, Car> smallCars) {
        this.smallCars = smallCars;
    }

    public Map<String, Car> getLargeCars() {
        return largeCars;
    }

    public void setLargeCars(Map<String, Car> largeCars) {
        this.largeCars = largeCars;
    }

    /***
     * @Description: method to generate a unique registration number.
     * @param
     * @return java.lang.String unique registration number
     * @throws
     * @author JayXu
     * @date 2019/10/25 10:36
     */
    public static String uniqueRegist(){
        String plate = NumericalUtil.generatePlate();
        if (REGISTRATIONS.contains(plate)) {
            uniqueRegist();
        }
        return plate;
    }

    /**
     * @Description:  This method returns the number of cars of the specified type that are available to rent.
     * @param carType
     * @return java.util.Map<java.lang.String,car.Car> available cars
     * @throws
     * @author JayXu
     * @date 2019/10/25 05:41
     */
    public Map<String,Car> availableCars(String carType){
        HashMap<String, Car> avlCars = new HashMap<>();
        if (carType.equals(CarFactory.LARGE_CAR)) {
            Map<String, Car> largeCars = fleet.getLargeCars();
            largeCars.forEach((s,car)->{
                LargeCar car1 =(LargeCar)car;
                if (!car1.isRented()) avlCars.put(s,car);
            });
        } else if (carType.equals(CarFactory.SMALL_CAR)) {
            Map<String, Car> smallCars = fleet.getSmallCars();
            smallCars.forEach((s,car)->{
                SmallCar car1 =(SmallCar)car;
                if (!car1.isRented()) avlCars.put(s,car); });
        } else {
            throw new IllegalArgumentException("Wrong type of car！");
        }
        return avlCars;
    }

    /**
     * @description: This method returns a collection of all the cars currently rented out (if any)
     * @param
     * @return java.util.List<car.Car>  a collection of all the cars currently rented out (if any)
     * @throws
     * @author JayXu
     * @date 2019/10/23 17:55
     */
    public List<Car> getRentedCars(){
        List<Car> cars = new ArrayList<Car>();
        RECORDS.forEach((s, car) ->cars.add(car));
        return cars;
    }

    /**
     * @description: Given a person, this method returns the car they are currently renting (if any).
     * @param  person who rent a car
     * @return car.Car  the specific car a person is currently renting (if any).
     * @throws
     * @author JayXu
     * @date 2019/10/23 17:50
     */
    public Car getCar(Person person){
        String number = person.getLicense().getLicenseNumber().getNumber();//JX-1990-100
        if (RECORDS.containsKey(number)){
            return RECORDS.get(number);
        }else{
            System.out.println(person.getFullName()+" doesn't have car rental service yet.");
            return null;
        }
    }

    /***
     * @Description: Given a Person (the rentor), the person's DrivingLicence and a specification of the type of car required (small or large),
     * this method determines whether the person is eligible to rent a car of the specified type and,
     * if there is a car available, issues a car of the specified type.
     * The car has a full tank of petrol at the start of the rental.
     * The method associates the car with the person renting it (so that the company has a record of cars out for rent
     * and the people renting them).
     * If a car cannot be issued, the method returns an appropriate indication of the failure to issue a car.
     * Note, this does not have to indicate why a car cannot be issued; it simply indicates that a car cannot be issued.
     *
     * The rules for determining whether a car can be issued or not are given below:
     *  the person renting the car must have a full driving licence
     *  they cannot rent more than one car at a time
     *  to rent a small car, they must be at least 20 years old and must have held their licence for at least 1 year
     *  to rent a large car, they must be at least 25 years old and must have held their licence for at least 5 years
     *
     * @param person who wants to rent a car.
     * @param drivingLicence this person's licence
     * @param typeOfCar what type of car the person wants to rent
     *
     * @return car.Car return a car the person demands
     * @throws
     * @author JayXu
     * @date 2019/10/24 21:03
     */
    public void issueCar(Person person, DrivingLicense drivingLicence, String typeOfCar) throws ParseException {
        int age = NumericalUtil.getIntervalYears(person.getDateOfBirth());
        int issue = NumericalUtil.getIntervalYears(drivingLicence.getDateOfIssue());
        boolean full = person.getLicense().isFull();//what is full driving license????
        String number = person.getLicense().getLicenseNumber().getNumber();
        //the person renting the car must have a full driving licence
        if (!full){
            System.out.println("Dear customer, you don't hold a full driving license, we cannot issue a car for you.");
        }
        //they cannot rent more than one car at a time
        if (RECORDS.containsKey(number)){
            System.out.println("Dear customer, you already have a contract with us.");
        }

        //whether the person is eligible to rent a car of the specified type
        if (CarFactory.SMALL_CAR.equals(typeOfCar)) {
            if (age >= 20 && issue >= 1) {
                Map<String,Car> smallCars= availableCars(typeOfCar);
                if(smallCars.size()>0){
                    Set<Map.Entry<String, Car>> entries = smallCars.entrySet();
                    Iterator<Map.Entry<String, Car>> iterator = entries.iterator();
                    Map.Entry<String, Car> next = iterator.next();
                    SmallCar sCar = (SmallCar)next.getValue();
                    sCar.setRented(true);
                    //sign the contract and record
                    RECORDS.put(number,sCar);
                }else{
                    System.out.println("Sorry, this is no car available.");
                }
            }else{
                System.out.println("Sorry, you are not eligible to rend a car.");
            }
        } else if (CarFactory.LARGE_CAR.equals(typeOfCar)) {
            if (age >= 25 && issue >= 5) {
                Map<String,Car> largeCars= availableCars(typeOfCar);
                if(largeCars.size()>0){
                    Set<Map.Entry<String, Car>> entries = largeCars.entrySet();
                    Iterator<Map.Entry<String, Car>> iterator = entries.iterator();
                    Map.Entry<String, Car> next = iterator.next();
                    LargeCar lCar = (LargeCar)next.getValue();
                    lCar.setRented(true);
                    //sign the contract and record
                    RECORDS.put(number,lCar);
                }else{
                    System.out.println("Sorry, this is no this type of car available.");
                }
            }else {
                System.out.println("Sorry, you are not eligible to rend a car yet.");
            }
        }
    }

    /**
     * @description: This method terminates the given person's rental contract.
     * In effect, the person is returning the car. The car is then available for rent by someone else.
     * The method removes the record of the rental from the company's records (disassociating the car from the person) and returns the amount of fuel in Litres required to fill the car's tank.
     * The person returning the car must either have returned the car with a full tank or will be liable for the number of Litres required to fill the tank.
     * This method is not responsible for managing charges for the required fuel.
     * It just reports the amount of fuel required to fill the tank.
     * If a person attempts to terminate a nonexistent contract, this method does nothing.
     *
     * @param person person who wants to terminate his or her rental contract
     * @return int   fuel required to fill the tank
     * @author JayXu
     * @date 2019/10/23 18:02
     */
    public int terminateRental(Person person){
        //fuel required to fill
        int fuelRequired = 0;
        //1. the person is returning the car. terminate record
        String number = person.getLicense().getLicenseNumber().getNumber();
        // 4. If a person attempts to terminate a nonexistent contract, this method does nothing.
        if (!RECORDS.containsKey(number)){
            System.out.println("Dear customer, you don't have contract yet.");
        }else{
            Car removedCar = RECORDS.remove(number);
            //2. The car is then available for rent by someone else.
            if (removedCar instanceof SmallCar){
                SmallCar smallCar = (SmallCar) removedCar;
                String registration = smallCar.getRegistration();
                int fuel = smallCar.getFuel();
                SmallCar smallCar1 = (SmallCar) fleet.getSmallCars().get(registration);
                smallCar1.setRented(false);
                int origin = smallCar1.getFuel();
                fuelRequired = origin - fuel;
                //call addFuel method
                smallCar1.addFuel(fuelRequired);
            }else if (removedCar instanceof LargeCar){
                LargeCar largeCar = (LargeCar) removedCar;
                String registration = largeCar.getRegistration();
                int fuel = largeCar.getFuel();
                LargeCar largeCar1 = (LargeCar) fleet.getLargeCars().get(registration);
                largeCar1.setRented(false);
                int origin = largeCar1.getFuel();
                fuelRequired = origin - fuel;
                //call addFuel method
                largeCar1.addFuel(fuelRequired);
            }
        }

        return fuelRequired;
    }
}
