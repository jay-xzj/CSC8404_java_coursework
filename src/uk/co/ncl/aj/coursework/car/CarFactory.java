package uk.co.ncl.aj.coursework.car;

/***
 * @Description: abstract class using static factory method to create cars
 * @author JayXu
 * @date 2019/10/25 10:26
 */
public abstract class CarFactory implements Car {
    public static final String SMALL_CAR = "small";//smallCar
    public static final String LARGE_CAR = "large";//largeCar
    private String registration;//registration number
    private int fuel;//current fuel in tank (Litres)
    private boolean rented; //is or is not be rented
    //private static Map<Integer, Car> FLEET = new HashMap<>();

    CarFactory(String registration, int fuel, boolean rented) {
        super();
        this.registration = registration;
        this.fuel = fuel;
        this.rented = rented;
    }

    /**
     * @Description: static CarFactory method to get instance of car
     * @param cartype car's two types' strings
     * @param registration car's registration number
     * @return uk.co.ucl.aj.coursework.car.Car
     * @throws
     */
    public static Car getInstance(String cartype, String registration) {
        Car car = null;
        if (cartype.equals(SMALL_CAR)) {
            //The car has a full tank of petrol at the start of the rental.
            car = new SmallCar(registration, SmallCar.SMALL_CAR_CAPACITY, false);
        } else if (cartype.equals(LARGE_CAR)) {
            //The car has a full tank of petrol at the start of the rental.
            car = new LargeCar(registration, LargeCar.LARGE_CAR_CAPACITY, false);
        } else {
            throw new IllegalArgumentException("invalid car type: " + cartype);
        }
        return car;
    }

    public String getRegistration() {
        return registration;
    }

    public int getFuel() {
        return fuel;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

}
