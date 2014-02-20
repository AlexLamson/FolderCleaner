package menusystem2;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import main.Updater;

public class ListFileButton extends BooleanButton
{
	public ListFileButton()
	{
		super();
	}
	
	public ListFileButton(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public ListFileButton(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		super(xPos, yPos, xSize, ySize, bool);
	}
	
	public ListFileButton(int xSize, int ySize, boolean bool)
	{
		super(xSize, ySize, bool);
	}
	
	public ListFileButton(int x, int y, int width, int height, String str)
	{
		super(x, y, width, height, str);
		strTrue = str;
		strFalse = str;
	}
	
	public ListFileButton(int xPos, int yPos, int xSize, int ySize, boolean bool, String str)
	{
		super(xPos, yPos, xSize, ySize, bool, str);
		strTrue = str;
		strFalse = str;
	}
	
	public ListFileButton(int xSize, int ySize, boolean bool, String str)
	{
		this(xSize, ySize, bool);
		strTrue = str;
		strFalse = str;
	}
	
	public ListFileButton(int x, int y, int width, int height, String strTrue, String strFalse)
	{
		super(x, y, width, height, strTrue);
		this.strTrue = strTrue;
		this.strFalse = strFalse;
	}
	
	public ListFileButton(int xPos, int yPos, int xSize, int ySize, boolean bool, String strTrue, String strFalse)
	{
		super(xPos, yPos, xSize, ySize, bool, strTrue);
		this.strTrue = strTrue;
		this.strFalse = strFalse;
	}
	
	public ListFileButton(int xSize, int ySize, boolean bool, String strTrue, String strFalse)
	{
		this(xSize, ySize, bool);
		this.strTrue = strTrue;
		this.strFalse = strFalse;
	}
	
	public void clicked3(boolean beingPressed)
	{
		openPath();
		
		super.clicked3(beingPressed);
	}
	
	public void openPath()
	{
		System.out.println("Trying to open path...");
		
		File file = Updater.getListFromID(ID).listPath;
		
		if(file.exists())
		{
			File dir = new File(file.getAbsolutePath());
			if(!file.isDirectory())
				dir = new File(file.getParent());
			
			try
			{
				Desktop.getDesktop().open(dir);
				System.out.println("success!");
				return;
			} catch (IOException e)
			{ e.printStackTrace(); }
		}
		System.out.println("failed.");
	}
}
