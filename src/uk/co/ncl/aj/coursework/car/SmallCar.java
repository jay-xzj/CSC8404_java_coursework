package uk.co.ncl.aj.coursework.car;

public class SmallCar extends CarFactory {
	public static final int SMALL_CAR_CAPACITY = 49;

    public SmallCar(String registration, int fuel, boolean rented) {
        super(registration, fuel, rented);
    }

	public final boolean isTankFull() {
		boolean boo = false;
		if(currentFuel() == SMALL_CAR_CAPACITY ) {
			boo = true;
		}
		return boo;
	}

	public final int currentFuel(){
		return getFuel();
	}

    public String getRegistNum(){
    	return getRegistration();
	}

    public int addFuel(int fuel) {
		int intank = this.getFuel();
		int addin = 0;
		if (intank==SMALL_CAR_CAPACITY){
			throw new IllegalArgumentException("The tank is full");
		}else {
			//must be less than capacity
			if (intank+fuel <= SMALL_CAR_CAPACITY){
				addin = fuel;
				this.setFuel(intank+fuel);
			}else if (intank+fuel > SMALL_CAR_CAPACITY){
				addin = SMALL_CAR_CAPACITY-intank;
				this.setFuel(SMALL_CAR_CAPACITY-intank);
			}
		}
		return addin;
    }

    public int getCapacity() {
        return SMALL_CAR_CAPACITY;
    }

	/***
	 * @Description: a method to "drive" the car for a given number of whole Kilometres that returns the
	 * number of whole Litres of fuel consumed during the journey.
	 *
	 * A small car consumes fuel at the rate of 20 Kilometres/Litre.
	 * A car cannot be driven if it is not currently rented.
	 * A car cannot be driven if it has 0 or less Litres of fuel in its tank.
	 *
	 * @param kms how many kilometres small car has been driven.
	 * @return int fuel that small car consumed
	 * @throws
	 * @author JayXu
	 * @date 2019/10/24 16:57
	 */
	public int drive(int kms) {
		if (kms<=0){
			throw new IllegalArgumentException("Mileage should bigger than 0!");
		}
		int consume = kms / 20;
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
}
