package unit2;
import java.util.*;
public class ThirdWord 
{
	public static void main(String[] args) 
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String sentence;
		String [] splitString;
		
		System.out.println("Type in a sentence:");
		sentence = sc.nextLine();
		splitString = sentence.split(" ");
		
		for (int i = 2; i < splitString.length; i += 3)
		{
			System.out.println(splitString[i]);
		}
	}
}
