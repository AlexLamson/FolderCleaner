package main;

import java.util.ArrayList;
import cleaner.Boolean;
import cleaner.ListFile;
import cleaner.Match;
import menusystem2.*;

public class ButtonChecker
{
	public static ArrayList<Boolean> buttonIDs = new ArrayList<Boolean>();
	
	public static void pressed1(Menu m)
	{
		buttonIDs.get((int)m.ID).bool = m.pressed1;
		if(!m.pressed1)
		{
//			System.out.println("wow, you pressed "+m.ID+"!");
			
			if(m instanceof BooleanButton)
			{
				if(((BooleanButton)m).strTrue.charAt(0) == 'O' && ((BooleanButton)m).strFalse.charAt(0) == 'X')
				{
					Match match = Updater.getMatchFromID(m.ID);
					if(!match.isNull())
						match.shouldDelete = !match.shouldDelete;
				}
				else
				{
					ListFile listFile = Updater.getListFromID(m.ID);
					if(!listFile.placeholder)
						listFile.useList.toggle();
				}
			}
			
			if(m instanceof Button)
			{
				Button b = ((Button)m);
				if(b.str.equals("Clean"))
				{
					Updater.clean();
				}
				else if(b.str.equals("Scan"))
				{
					Updater.scan();
				}
			}
			
			switch((int)m.ID)
			{
//			case 1:
//				System.out.println("One is a cool number");
//				break;
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
