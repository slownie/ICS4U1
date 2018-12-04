package unit1;
import java.util.Scanner;
public class SplittingDigits 
{
	public static void main (String [] args)
	{
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int digit;
		int a;
		int b;
		
		System.out.println("Enter a Number with ONLY two digits:");
		digit = sc.nextInt();
		
		if (digit >= 100 || digit <= -100)
		{
			System.out.println("Looks like SOMEBODY couldn't follow instructions.");
			System.exit(0);
		}
		
		//Method 1:
		a = digit/10;
		b = digit%10;
		System.out.println(a+" "+b);
		
		//Method 2:
		String split = String.valueOf(digit);
		System.out.println(split.charAt(0)+" "+split.charAt(1));
		
		//Method 3:
	}
}
