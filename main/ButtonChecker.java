package main;

import java.util.ArrayList;
import cleaner.Boolean;
import menusystem2.*;

public class ButtonChecker
{
	public static ArrayList<Boolean> buttonIDs = new ArrayList<Boolean>();
	
	public static void pressed1(Menu m)
	{
		if(m.pressed1)
		{
			System.out.println("wow, you pressed "+m.ID+"!");
			
			switch((int)m.ID)
			{
			case 1:
				System.out.println("One is a cool number");
				break;
			default:
				
				break;
			}
		}
	}
	
	public static void pressed2(Menu m)
	{
		if(m.pressed2)
		{
			switch((int)m.ID)
			{
			case 1:
				
				break;
			default:
				
				break;
			}
		}
	}
	
	public static void pressed3(Menu m)
	{
		if(m.pressed2)
		{
			switch((int)m.ID)
			{
			case 1:
				
				break;
			default:
				
				break;
			}
		}
	}
	
	public static void addID()
	{
		buttonIDs.add(new Boolean(false));
	}
}
