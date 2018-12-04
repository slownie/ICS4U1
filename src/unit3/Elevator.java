/* Samuel Lownie
 * September 27th 2018
 */
package unit3;
public class Elevator 
{
	final static String manufacturer = "Otis";
	final static int topFloor = 19;
	final static int maxOccupants = 25;
	static boolean powerOn = true;
	
	private int floor = 1;
	private int people = 0;
	private boolean doorsOpen = false;
	
	public Elevator()
	{
		
	}
	
	public Elevator(int floor)
	{
		this.floor = floor;
		if (floor > topFloor || floor <= 0)
		{
			floor = 1;
		}
	}
	
	static boolean setPowerState(boolean power)
	{
		if (!power)
		{
			System.out.println("Turning Off the Power...");
			powerOn = false;
		} else {
			System.out.println("Turning On the Power...");
			powerOn = true;
		}
		
		return powerOn;
	}
	
	static void getPowerState()
	{
		if (powerOn)
		{
			System.out.println("The Power is currently On.");
		} else {
			System.out.println("The Power is currently Off.");
		}
	}
	
	void up()
	{
		if (powerOn)
		{
			if (!doorsOpen)
			{
				floor++;
				if (floor > topFloor)
				{
					floor = topFloor;
					System.out.println("Error: Elevator cannot go higher than Floor "+topFloor+".");
				} else {}
			} else {
				System.out.println("Error: Doors are open. Close them.");
			}
		} else {
			System.out.println("Error: Power is off. Turn it on.");
		}
	}
	
	void down()
	{
		if (powerOn)
		{
			if (!doorsOpen)
			{
				floor--;
				if (floor <= 0)
				{
					floor = 1;
					System.out.println("Error: Elevator cannot go lower than Floor 0");
				} else {}
			} else {
				System.out.println("Error: Doors are open. Close them.");
			}
		} else {
			System.out.println("Error: Power is off. Turn it on.");
		}
	}
	
	void upN(int n)
	{
		if (powerOn)
		{
			if (!doorsOpen)
			{
				floor += n;
				if (floor > topFloor)
				{
					floor = topFloor;
					System.out.println("Error: Elevator cannot go higher than Floor "+topFloor+".");
				} else {}
			} else {
				System.out.println("Error: Doors are open. Close them.");
			}
		} else {
			System.out.println("Error: Power is off. Turn it on.");
		}
	}
	
	void downN(int n)
	{
		if (powerOn)
		{
			if (!doorsOpen)
			{
				if (n < 0)
				{
					System.out.println("Error: Cannot go down by a negative number.");
				} else {
						floor -= n;
						if (floor <= 0)
						{
							floor = 1;
							System.out.println("Error: Elevator cannot go lower than Floor 1");
						} else {}
					}
			} else {
				System.out.println("Error: Doors are open. Close them.");
			}	
		} else {
			System.out.println("Error: Power is off. Turn it on.");
		}
	}
	
	void openDoors()
	{
		if (powerOn)
		{
			doorsOpen = true;
			
		} else {
			System.out.println("Error: Power is off. Turn it on.");
		}
	}
	
	void closeDoors()
	{
		if (powerOn)
		{
			doorsOpen = false;
			
		} else {
			System.out.println("Error: Power is off. Turn it on.");
		}
	}
	
	void addPeople(int n)
	{
		if (doorsOpen)
		{
			people += n;
			if (people > maxOccupants)
			{
				people -= n;
				System.out.println("Error: Too many people on the Elevator.");
			} else {}
		} else {
			System.out.println("Error: Doors are closed. Open them.");
		}
	}
	
	void removePeople(int n)
	{
		if (doorsOpen)
		{
			people -= n;
			if (n <= 0)
			{
				System.out.println("Error: Cannot remove Negative People from the Eleavtor.");
			} 
			if (people < 0) people = 0;

		} else {
			System.out.println("Error: Doors are closed. Open them.");
		}
	}
	
	public String printString()
	{
		String s = " Manufacturer: "+manufacturer+". Floor: "+floor+". Number of People: "+people+". Doors Open: "+(doorsOpen ? "Open." : "Closed.");
		s = "Building Power: "+(powerOn ? "ON." : "OFF.")+s;
		return s;
	}
}

/* Marking: 21/25

setPower state is not supposed to print anything if the state of the power does not change.

getpowerstate is supposed to return a T/F.  That's what get() methods do, they return soemthing,

printstring is not a special method. toString is. Every class has a toString method and it's normally supposed to make sense. Most of the time we don't bother but for some objects we need to.

Everything else is good
*/
