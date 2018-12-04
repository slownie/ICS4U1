package unit3;

public class AnotherObjectProgram 
{
	public static void main(String[] args) 
	{
		method1();
		new AnotherObjectProgram();
	}
	
	static void method1()
	{
		System.out.println("1");
	}
	
	//constructer:
	AnotherObjectProgram()
	{
		method2();
	}
	
	void method2()
	{
		System.out.println("2");
	}
}
