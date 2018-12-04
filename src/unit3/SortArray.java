/* Samuel Lownie
 * October 3rd 2018
 * Sorts an Array of 25 numbers ranging from 1000 to 1.
 */
package unit3;
public class SortArray 
{
	public static void main (String [] args)
	{
		int array [] = new int [25];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = (int)(Math.random() * 1000) + 1;
			System.out.print(array[i]+" ");
		}
		sortArray(array);
	}
	
	static void sortArray(int array[])
	{
		System.out.println("\nSorted Array:");
		//canSwap must be initialized to start the while loop:
		boolean canSwap = true;  
		//Holding Number:
	
	    while(canSwap)
	    {
	    	/* canSwap is set to false while waiting for a swap possible to occur.
	    	 * If there isn't a swap that can occur, the loop stops and the
	    	 * array should be fully sorted in ascending order:
	    	 */
	        canSwap = false;  
	        for(int i = 0; i < array.length - 1; i++)
	        {
	           //If the value of array at i is greater than the value of array at i + 1, the swapping process starts:
               if (array[i] > array[i+1])
               {
            	  //temp stores the value of array at i:
                  int temp = array[i];
	              array[i] = array[i+1];
	              array[i+1] = temp;
	              canSwap = true; 
               } 
	        } 
	    }
	    
	    //Prints out the Sorted Array:
	    for (int h = 0; h < array.length; h++)
	    {
	    	System.out.print(array[h]+" ");
	    }
	}
}
