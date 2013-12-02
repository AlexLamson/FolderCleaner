package menusystem2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Main;

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
	
	public double round(double num, int places)
	{
		return (1.0*(int)(num*Math.pow(10, places)))/Math.pow(10, places);
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
		//background color
		g.setColor(bgcolor);
		g.fillRect(x, y, width, height);
		
		//bar color
		g.setColor(fgcolor);
		g.fillRect(x, y, (int)((currentVal/maxVal)*width), height);
		
		//fancy trim
		g.setColor(Button.changeColor(fgcolor, 20));
		g.fillRect(x, y, (int)((currentVal/maxVal)*width), height/12);
		g.setColor(Button.changeColor(fgcolor, -20));
		g.fillRect(x, y+height-height/12, (int)((currentVal/maxVal)*width), height/12);
		
		//current percentage
		String str = round(100*currentVal/maxVal, 0)+"";
		
		Font f = new Font("Verdana", Font.PLAIN, 28/Main.pixelSize);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		Rectangle2D rect = fm.getStringBounds(str, g);
		
		int xCenter = x + (width/2);
		int yCenter = y + (height/2);
		
		int sX = xCenter - (int)(rect.getWidth()/2);
		int sY = yCenter - (int)(rect.getHeight()/2) + fm.getAscent();

		g.setColor(Color.gray);
//		g.setColor(new Color(255-bgcolor.getRed(), 255-bgcolor.getGreen(), 255-bgcolor.getBlue()));
		
		g.drawString(str, sX, sY);
		
		//pass to lower menus
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
}
