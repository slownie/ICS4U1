/* Samuel Lownie
 * September 27th 2018
 */
package unit3;
import unit3.Elevator;
public class ElevatorTest
{
	public static void main (String [] args)
	{
		Elevator EastElevator = new Elevator(5);
		Elevator SouthElevator = new Elevator();
		
		//South Elevators:
		SouthElevator.openDoors();
		SouthElevator.addPeople(10);
		System.out.println(SouthElevator.printString());
		SouthElevator.closeDoors();
		SouthElevator.upN(3);
		SouthElevator.openDoors();
		SouthElevator.removePeople(3);
		System.out.println(SouthElevator.printString());
		SouthElevator.closeDoors();
		SouthElevator.upN(15);
		SouthElevator.openDoors();
		SouthElevator.removePeople(7);
		System.out.println(SouthElevator.printString());
		SouthElevator.closeDoors();
		//East Elevator:
		EastElevator.openDoors();
		EastElevator.addPeople(8);
		System.out.println(EastElevator.printString());
		EastElevator.addPeople(30);
		System.out.println(EastElevator.printString());
		EastElevator.closeDoors();
		EastElevator.upN(20);
		EastElevator.downN(-5);
		//Both Elevators:
		SouthElevator.downN(17);
		EastElevator.downN(17);
		Elevator.setPowerState(false);
		Elevator.getPowerState();
		System.out.println(SouthElevator.printString());
		System.out.println(EastElevator.printString());
		SouthElevator.openDoors();
		EastElevator.openDoors();
		SouthElevator.up();
		EastElevator.down();
		Elevator.setPowerState(true);
		SouthElevator.downN(1);
		for (int i = 0; i < 9; i++)
		{
			SouthElevator.up();
			SouthElevator.openDoors();
			SouthElevator.closeDoors();
		}
		System.out.println(SouthElevator.printString());
	}
}