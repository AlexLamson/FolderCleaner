package menusystem2;

import java.awt.Color;
import java.awt.Graphics;

public class Loader extends Menu
{
	public double currentVal = 0, maxVal = 100;
	public Color fgcolor = Color.green;
	
	public Loader(int x, int y, int width, int height)
	{
		super(x, y, width, height);
//		fgcolor = new Color(random(0,255), random(0, 255), random(0,255));
	}
	
	public Loader(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		super(xPos, yPos, xSize, ySize, bool);
	}
	
	public void setMax(double val)
	{
		maxVal = val;
	}
	
	public void setCurrent(double val)
	{
		currentVal = val;
	}
	
	public void tick()
	{
		super.tick();
		
		currentVal++;
		if(currentVal > maxVal)
			currentVal = maxVal;
		else if(currentVal < 0)
			currentVal = 0;
		
		if(pressed1)
			currentVal = 0;
	}
	
	public void render(Graphics g)
	{
		g.setColor(bgcolor);
		g.fillRect(x, y, width, height);
		
		g.setColor(fgcolor);
		g.fillRect(x, y, (int)((currentVal/maxVal)*width), height);
		
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
}
