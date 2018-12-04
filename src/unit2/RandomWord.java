/* Samuel Lownie
 * September 20th 2018
 * Creates a random 6 letter string that only has one or two vowels in it.
 */
package unit2;
import java.util.*;
public class RandomWord
{
    public static void main (String [] args)
    {
        Random r = new Random();
        String randomWord = "";
        String vowels = "aeiouy";
        int vowelCount = 0;

        while (vowelCount < 1 || randomWord.length() < 6)
        {
            char letter = (char)(r.nextInt(26)+'a');
            //If the generatedWord equals any vowel, the vowelCount is increased by 1:
            boolean isVowel = (vowels.indexOf(letter) > 0);

            if (isVowel)
            {
            	//Skips if there are already 2 vowels in the string, adds it to the word otherwise:
                if (vowelCount > 2) continue; 
                vowelCount++;
                randomWord += letter;
            }
            else
            {
            	//Skips if there are 5 characters in the string and none of them are vowels:
                if (randomWord.length() == 5 && vowelCount == 0) continue; 
                randomWord += letter;
            }
        }
        
        System.out.println(randomWord);
    }
}
