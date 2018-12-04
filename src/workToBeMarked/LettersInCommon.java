/* Samuel Lownie
 * October 19th 2018
 * Given a list of 6 letter words and another 6 letter word,
 * prints the total number of words in common.
 */
package workToBeMarked;

public class LettersInCommon 
{
	public static void main (String [] args)
	{
		String wordList[] = new String[]{"guyana", "lotion", "chilly", 
										 "doggie", "fledgy", "swanky",
										 "crawly", "physic"};
		String word = "digger";
		int [] wordCount = new int [wordList.length];
		char position1;
		char position2;
		
		//The First For Loop is for each of word's Characters:
		for (int f = 0; f < 6; f++)
		{
			position1 = word.charAt(f);
			
			//The Second For Loop is for each of the elements in wordList:
			for (int h = 0; h < wordList.length; h++)
			{
				//The Third For Loop is for each of wordList[h]'s Characters:
				for (int i = 0; i < 6; i++)
				{
					position2 = wordList[h].charAt(i);
					
					if (position1 == position2)
					{
						wordCount[h]++;
					}
				}
			}
		}
		
		//Prints out the word, and wordList's corresponding wordCount:
		System.out.println("Word: "+word);
		System.out.println("Word Count:");
		for (int i = 0; i < wordList.length; i++)
		{
			System.out.println(wordList[i]+" - "+wordCount[i]);
		}
	}
}

/* Marking. 16/20

Nice output. Good comments

The number of letters in common is wrong!

*/
