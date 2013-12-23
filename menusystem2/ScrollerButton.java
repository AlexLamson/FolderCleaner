package menusystem2;

import java.awt.Point;

public class ScrollerButton extends Button
{
	public int[] times = {1, 2, 5, 10, 20, 30, 60, 2*60, 3*60, 4*60, 6*60, 12*60, 24*60, 2*24*60, 7*24*60};
	public int arrayPos = 5;
	public String realStr = "";
	public static final String numString = "~num~", prettyNumString = "~pnum~";
	
	public ScrollerButton()
	{
		super();
	}
	
	public ScrollerButton(int x, int y, int width, int height, String str)
	{
		super(x, y, width, height, str);
		realStr = new String(str);
		setDisplayString();
	}
	
	public ScrollerButton(int xPos, int yPos, int xSize, int ySize, boolean bool, String str)
	{
		super(xPos, yPos, xSize, ySize, bool, str);
		realStr = new String(str);
		setDisplayString();
	}
	
	public ScrollerButton(int xSize, int ySize, boolean bool)
	{
		super(xSize, ySize, bool);
	}
	
	public void setDisplayString()
	{
		int num = times[arrayPos];
		String numStr = ""+num;
		String pnumStr = Loader.getTimeFromSeconds(num*60);
		str = realStr.replaceAll(numString, numStr);
		str = str.replaceAll(prettyNumString, pnumStr);
	}
	
	//returns true if this menu actually scrolled something
	public boolean scroll(Point p, boolean scrollingUp)
	{
		if(contains(p))
		{
			changePos(scrollingUp);
			setDisplayString();
			return true;
		}
		return false;
	}
	
	public void changePos(boolean moveUp)
	{
		if(moveUp)
		{
			arrayPos++;
			if(arrayPos > times.length-1)
				arrayPos = times.length-1;
		}
		else
		{
			arrayPos--;
			if(arrayPos < 0)
				arrayPos = 0;
		}
	}
}
