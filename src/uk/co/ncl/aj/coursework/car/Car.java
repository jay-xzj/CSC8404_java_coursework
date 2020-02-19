package uk.co.ncl.aj.coursework.car;

public interface Car {

	/**
	 * @Description: method to show is tank full or not full.
	 * @param []
	 * @return boolean tank is full or not
	 * @throws
	 * @author JayXu
	 * @date 2019/10/25 10:25
	 */
	boolean isTankFull();

	/**
	 * @Description: method to indicate how much fuel still in tank.
	 * @param
	 * @return int how much fuel in tank now
	 * @throws
	 * @author JayXu
	 * @date 2019/10/25 10:25
	 */
	int currentFuel();

	String getRegistNum();
	
	/**
	 * Add a given number of whole Litres to the fuel tank(up to tank's capacity)
	 * And, after execution, indicates how much fuel was added to the tank.
	 * @param litres
	 * @return
	 */
	int addFuel(int litres);

	/**
	 * @Description: Method to indicate how much fuel will be consumed by running a certain distance.
	 * @param kms Distance that car will run.
	 * @return int How much fuel will be consumed.
	 * @throws
	 * @author JayXu
	 * @date 2019/10/25 10:21
	 */
	int drive(int kms);

	/***
	 * @Description: method to get the capacity of the car
	 * @param
	 * @return int capacity of the car
	 * @throws
	 * @author JayXu
	 * @date 2019/10/25 10:23
	 */
	int getCapacity();

}
