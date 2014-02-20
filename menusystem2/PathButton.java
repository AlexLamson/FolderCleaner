package menusystem2;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;

import main.Main;

//left click to open path, right click to copy path to clipboard

public class PathButton extends Button
{
	public PathButton()
	{
		super();
	}
	
	public PathButton(int x, int y, int width, int height, String str)
	{
		super(x, y, width, height, str);
	}
	
	public PathButton(int xPos, int yPos, int xSize, int ySize, boolean bool, String str)
	{
		super(xPos, yPos, xSize, ySize, bool, str);
	}
	
	public PathButton(int xSize, int ySize, boolean bool, String str)
	{
		super(xSize, ySize, bool, str);
	}
	
	public void clicked1(boolean beingPressed)
	{
		if(!beingPressed && Main.menu.getSubMenu(Main.prevmse) == this)
			openPath();
		
		super.clicked1(beingPressed);
	}
	
	public void clicked3(boolean beingPressed)
	{
		copyPath();
		
		super.clicked3(beingPressed);
	}
	
	public void openPath()
	{
		System.out.println("Trying to open path...");
		
		File file = new File(str);
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
	
	public void copyPath()
	{
		StringSelection selection = new StringSelection(str);
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    clipboard.setContents(selection, selection);
	}
}
