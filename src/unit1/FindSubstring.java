package unit1;
import java.util.Scanner;
/* Program to get a sentence and then a substring.
 * Print out the letter immediately before substring
 * and immediately after.
 */
public class FindSubstring
{
	public static void main (String [] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		String sentence;
		String subString;
		int a;
		int b;
		int c;
		
		System.out.println("Type in a Sentence:");
		sentence = sc.nextLine();
		
		if (sentence.equals(""))
		{
			System.out.println("Error.");
			System.exit(500);
		}
		
		System.out.println("Type in a SubString:");
		subString = sc.nextLine();
		
		if (sentence.contains(subString))
		{
			a = sentence.indexOf(subString);
			b = a - 1;
			c = a + subString.length();
			
			if (b < 0)
			{
				System.out.println(b+" "+c);
				System.out.println("*"+" "+sentence.charAt(c));
			} else {
				System.out.println(b+" "+c);
				System.out.println(sentence.charAt(b)+" "+sentence.charAt(c));
			}
			
		} else {
			System.out.println("Not Found.");
		}
	}
}
