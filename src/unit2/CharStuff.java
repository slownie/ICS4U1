package unit2;
/* This shows how to use char <--> int for manipulating text, removing punctuation, ... 
 */
import java.util.Arrays;
public class CharStuff 
{
	public static void main(String[] args) 
	{
		
		String text = "Good afternoon! It is 12:47 pm";
		text = text.toUpperCase();
		char c = text.charAt(3);  //first letter of string
		
		int n = c;
		System.out.println(c + " " + n); // c + " " makes a string, then add n to the string
		System.out.println(c + n); //add two numbers, then print them
		
		System.out.println(c + '=');
		System.out.println("" + c + '=');
		
		for (int i = 0; i < 26; i++) 
		{ 
			char cc = (char) ('A' + i);
			System.out.print(cc);
		}
		
		String text2 = "";
		for (int i=0; i<text.length(); i++) 
		{
			char cc = text.charAt(i);
			if (cc >= 'A' && cc <= 'Z') text2 += cc; //keep all letters
			if (cc == ' ') text2 += cc;	//add in spaces
			if (cc >= '0' && cc <= '9') text2 += cc; //add in numbers
		}
		System.out.println('\n');
		System.out.println(text);		
		System.out.println(text2);
		
		/* ##### Really cool way of counting letters ###### */
		int totals[] = new int[26]; //and array of 26 ints
		for (int i=0; i<text.length(); i++) {
			char cc = text.charAt(i);
			if (cc < 'A' || cc > 'Z') continue;
			
			totals[cc-65]++;	
			/* A adds 1 to element 0  (A is changed to 0, B is changed to 1, ...) 
			   B adds 1 to element 1
			   C adds 1 to element 2 ...			 */
		}
		
		for(int i='A'; i < 'A'+26; i++) {
			System.out.print(" "+(char)(i) + ",");
		}
		System.out.println("\n"+Arrays.toString(totals));
		
	
	}
}