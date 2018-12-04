package unit2;
import java.util.*;
public class VowelCount 
{
	public static void main(String[] args) 
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner (System.in);
		String word;
		String uppercaseWord;
		char l;
		int vowelCount = 0;
		
		System.out.println("Type in a word:");
		word = sc.nextLine();
		uppercaseWord = word.toUpperCase();
		
		for (int i = 0; i < uppercaseWord.length(); i++)
		{
			l = uppercaseWord.charAt(i);
			if (l == 'A' || l == 'E' || l == 'I' || l == 'O' || l == 'I' || l == 'Y')
			{
				++vowelCount;
			}
		}
		System.out.println("Vowel Count: "+vowelCount);
	}
}