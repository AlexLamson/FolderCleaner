package menusystem2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Main;

public class Loader extends Menu
{
	public boolean test = true;
	
	public double currentVal = 0, maxVal = 100;
	public Color fgcolor = Color.green;
	
	public int currentTime = 0, checkTime = 1;	//check every 4 ticks (remember its running at 20 ticks per second)
	public Buffer buffer = new Buffer(100);
	
	public Loader()
	{
		super();
	}
	
	public Loader(int x, int y, int width, int height)
	{
		super(x, y, width, height);
//		fgcolor = new Color(random(0,255), random(0, 255), random(0,255));
	}
	
	public Loader(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		super(xPos, yPos, xSize, ySize, bool);
	}
	
	public Loader(int xSize, int ySize, boolean bool)
	{
		super(xSize, ySize, bool);
	}
	
	public void reset()
	{
		currentVal = 0;
		buffer.clear();
	}
	
	public void setMax(double val)
	{
		maxVal = val;
	}
	
	public void setCurrent(double val)
	{
		currentVal = val;
	}
	
	public void setBufferSize(int size)
	{
		buffer.setBufferSize(size);
	}
	
	public void clicked1(boolean beingPressed)
	{
		super.clicked1(beingPressed);
		
		if(test && !beingPressed)
			reset();
	}
	
	public void tick()
	{
		super.tick();
		
		if(test)
		{
			currentVal += NumGen.random(0, 2);
			
			if(currentVal > maxVal)
				currentVal = maxVal;
			else if(currentVal < 0)
				currentVal = 0;
		}
		
		currentTime++;
		if(currentTime >= checkTime && currentVal < maxVal)
		{
			buffer.addVal(currentVal);
			currentTime = 0;
		}
		if(currentTime > checkTime)
			currentTime = 0;
	}
	
	public void render(Graphics g)
	{
		//background color
		g.setColor(bgcolor);
		g.fillRect((int)x, (int)y, width, height);
		
		//bar color
		g.setColor(fgcolor);
		g.fillRect((int)x, (int)y, (int)((currentVal/maxVal)*width), height);
		
		//fancy trim
		g.setColor(ColorGen.changeColor(fgcolor, 20));
		g.fillRect((int)x, (int)y, (int)((currentVal/maxVal)*width), height/12);
		g.setColor(ColorGen.changeColor(fgcolor, -20));
		g.fillRect((int)x, (int)y+height-height/12, (int)((currentVal/maxVal)*width), height/12);
		
		//draw the time remaining or the percent complete
		String str = "";
		if(currentVal != 0)
		{
			//current percentage
//			String str = round(100*currentVal/maxVal, 0)+" %";
			
			//completion ETA
			double secondsLeft = buffer.getNStepsToVal(maxVal)/((1000/Main.tickTime) / checkTime);
			str = StringTime.secToString(secondsLeft)+" remaining";
		}
		if(str.length() != 0)
		{
			Font f = new Font("Verdana", Font.PLAIN, 24/Main.pixelSize);
			g.setFont(f);
			FontMetrics fm = g.getFontMetrics(f);
			Rectangle2D rect = fm.getStringBounds(str, g);
			int xCenter = (int)x + (width/2);
			int yCenter = (int)y + (height/2);
			int sX = xCenter - (int)(rect.getWidth()/2);
			int sY = yCenter - (int)(rect.getHeight()/2) + fm.getAscent();
			
			//put a box behind the percentage
			g.setColor(new Color(200, 200, 200, 200));
			g.fillRect(sX-3, sY-fm.getAscent()-3, (int)rect.getWidth()+2*3, (int)rect.getHeight()+2*3);
			
			//draw the string
			g.setColor(Color.black);
//			g.setColor(new Color(255-bgcolor.getRed(), 255-bgcolor.getGreen(), 255-bgcolor.getBlue()));
			g.drawString(str, sX, sY);
		}
		
		super.render(g);
	}
	
	public String toString()
	{
		return "Loader("+xPos+", "+yPos+")";
	}
}
