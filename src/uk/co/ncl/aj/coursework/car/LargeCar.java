package uk.co.ncl.aj.coursework.car;

/**
* @description: Large car class
* @author JayXu
* @date 2019/10/25 10:49
*/
public class LargeCar extends CarFactory {

    public static final int LARGE_CAR_CAPACITY = 60;// capacity of the large car's tank

    LargeCar(String registration, int fuel, boolean rented) {
        super(registration, fuel, rented);
    }

    /***
     * Is the tank full or not
     */
    public final boolean isTankFull() {
        boolean boo = false;
        if(currentFuel() == LARGE_CAR_CAPACITY ) {
            boo = true;
        }
        return boo;
    }

    /**
     * get current volume of fuel int the tank
     */
    public final int currentFuel(){
        return getFuel();
    }

    /***
     * get registration number
     */
    public String getRegistNum(){
        return getRegistration();
    }

    /***
     * method to add fuel into the tank
     */
    public final int addFuel(int fuel) {
        int intank = this.getFuel();
        int addin = 0;
        if (intank==LARGE_CAR_CAPACITY){
            throw new IllegalArgumentException("The tank is full");
        }else {
            //must be less than capacity
            if (intank+fuel <= LARGE_CAR_CAPACITY){
                addin = fuel;
                this.setFuel(intank+fuel);
            }else if (intank+fuel > LARGE_CAR_CAPACITY){
                addin = LARGE_CAR_CAPACITY-intank;
                this.setFuel(LARGE_CAR_CAPACITY-intank);
            }
        }
        return addin;
    }

    /***
     * @Description: a method to "drive" the car for a given number of whole Kilometres that returns
     * the number of whole Litres of fuel consumed during the journey.
     *
     * 1. A large car consumes fuel at the rate of 10 Kilometres/Litre for the first 50 Kilometres
     * of a journey and then at the rate of 15 Kilometres/Litre for the remainder of the journey.
     *
     * @param kms kilometres small car will be driven
     * @return int fuel consumed by driving kms kilometres
     * @throws
     * @author JayXu
     * @date 2019/10/24 16:47
     */
    public final int drive(int kms) {
		if (kms<=0){
			throw new IllegalArgumentException("Mileage should bigger than 0!");
		}
        int consume = 0;
        if (kms <= 50) {
            consume = kms / 10;
        } else {
            consume = 5 + (kms - 50) / 15;
        }
		int fuel = this.getFuel();
		if (this.isRented() && fuel>0 && fuel > consume) {
			//deduct consumed fuel from remain fuel.
			fuel = fuel - consume;
			this.setFuel(fuel);
			return consume;
		} else {
			System.out.println("There is no journey has been undertaken.");
			return 0;
		}
    }

    /**
     * get capacity of the tank
     */
    public int getCapacity() {
        return LARGE_CAR_CAPACITY;
    }

}
