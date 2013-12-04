package menusystem2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Main;

public class Loader extends Menu
{
	public double currentVal = 0, maxVal = 333;
	public Color fgcolor = Color.green;
	
	public int currentTime = 0, checkTime = 7;	//check every 4 ticks (remember its running at 20 ticks per second)
	public Buffer buffer = new Buffer(50);
	
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
	
	public static double round(double num, int places)
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
		{
			currentVal = 0;
			buffer.clear();
		}
		
//		System.out.println(currentTime +" "+ checkTime +" "+ currentVal +" "+ maxVal);
		
		currentTime++;
		if(currentTime >= checkTime && currentVal < maxVal)
		{
			buffer.addVal(currentVal);
			currentTime = 0;
			
//			System.out.println("A " + round((1000.0/Main.tickTime)*buffer.getNStepsToVal(maxVal)/(21), 2));
//			System.out.println("B " + round((1000.0/Main.tickTime)*buffer.getNStepsToVal(maxVal), 2));
//			System.out.println("C " + buffer.getNStepsToVal(maxVal));
		}
		if(currentTime > checkTime)
			currentTime = 0;
			
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
//		String str = round(100*currentVal/maxVal, 0)+" %";
		//completion ETA
		String str = round((1000.0/Main.tickTime)*buffer.getNStepsToVal(maxVal)/(1.0), 1)+" time units";
		
		Font f = new Font("Verdana", Font.PLAIN, 28/Main.pixelSize);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		Rectangle2D rect = fm.getStringBounds(str, g);
		int xCenter = x + (width/2);
		int yCenter = y + (height/2);
		int sX = xCenter - (int)(rect.getWidth()/2);
		int sY = yCenter - (int)(rect.getHeight()/2) + fm.getAscent();
		
		//put a box behind the percentage
		g.setColor(new Color(200, 200, 200, 200));
		g.fillRect(sX, sY-fm.getAscent(), (int)rect.getWidth(), (int)rect.getHeight());
		
		g.setColor(Color.black);
//		g.setColor(new Color(255-bgcolor.getRed(), 255-bgcolor.getGreen(), 255-bgcolor.getBlue()));
		g.drawString(str, sX, sY);
		
		//pass to lower menus
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
}
