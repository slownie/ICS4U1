package unit3;
import unit3.Item;
import java.util.*;
public class GroceryArray 
{
	public static void main (String [] args)
	{
		ArrayList<Item> list = new ArrayList<Item>();
		list.add(new Item(2.99, "1 Basket of", "Strawberries"));
		list.add(new Item(1.75, "3 Bunches of", "Bananas"));
		list.add(new Item(1.99, "3 Bags of", "Milk"));
		list.add(new Item(6.99, "2 Packs of", "Bacon"));
		System.out.println("Grocery List:");
		printList(list);
		removeItem(list);
		System.out.println("Grocery List #2:");
		printList(list);
	}
	
	static void printList(ArrayList<Item> list)
	{
		System.out.println("Price:\tAmount:\t\tItem:");
		for (int i = 0; i < list.size(); i++)
		{
			Item l = list.get(i);
			System.out.println("$"+l.price+"\t"+l.amount+"\t"+l.item);
		}
	}
	
	static void removeItem(ArrayList<Item> list)
	{
		System.out.println("Too Expensive!");
		for (int h = 0; h < list.size(); h++)
		{
			Item l = list.get(h);
			if (l.price > 5.99) 
			{
				list.remove(h);
				h--;	
			}
		}
	}
}
