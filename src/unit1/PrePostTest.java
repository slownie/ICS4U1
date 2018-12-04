package unit1;
public class PrePostTest 
{
	public static void main (String args[]) 
	{
	   int x=5, y=5;
	   System.out.printf("x,y:\t\tx=%d, y=%d\n",x,y);
	   y=x++;
	   System.out.printf("y=x++\t\tx=%d, y=%d\n\n",x,y);
	   x=y=5;
	   System.out.printf("x,y:\t\tx=%d, y=%d\n",x,y);
	   y=++x;
	   System.out.printf("y=++x\t\tx=%d, y=%d\n\n",x,y);
	   System.out.printf("x,y:\t\tx=%d, y=%d\n",x,y);
	   ++x;
	   y++;
	   System.out.printf("++x, y++\tx=%d, y=%d\n",x,y);
	}
}