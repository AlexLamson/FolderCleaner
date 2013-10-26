package menuSystem;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import main.Main;

public class Button
{
	public static final Button placeholder = new Button(true);
	
	public static int largestID = 0;
	public int id;
	
	public Color bgColor, fgColor;	//background color and text color of the button
	public int x, y, width, height;	//dimensions of a rectangle that represents the button
	public String text;		//the text that that is displayed in the button
	
	public boolean pressed;		//true if you are currently clicking on the button
	
	//constructors
	
	//make a placeholder button
	public Button(boolean placeholder)
	{
		this();
		id = -1;
	}
	
	public Button()
	{
		id = largestID;
		largestID++;
		
		x = 0;
		y = 0;
		width = 1;
		height = 1;
		
		// text = "ID: "+id;
		text = Prob17.numToString(id, true);
		
		
		// bgColor = new Color(100, 100, 100);
		bgColor = new Color(0, 64, 128);
		fgColor = new Color(0, 0, 0);
		
		pressed = false;
	}
	
	public Button(int x, int y, int width, int height)
	{
		this();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Button(int x, int y, int width, int height, String text)
	{
		this(x, y, width, height);
		this.text = text;
	}
	
	public Button(int x, int y, int width, int height, String text, Color bgColor, Color fgColor)
	{
		this(x, y, width, height, text);
		this.bgColor = bgColor;
		this.fgColor = fgColor;
	}
	
	public void press()
	{
		pressed = true;
	}
	
	public void unpress()
	{
		pressed = false;
	}
	
	public void togglePress()
	{
		pressed = !pressed;
	}
	
	public boolean contains(Point p)
	{
		Rectangle rect = new Rectangle(x, y, width, height);
		return rect.contains(p);
	}
	
	//limit a number to range [0-255]
	public int limit(int num)
	{
		if(num < 0)
			num = 0;
		else if(num > 255)
			num = 255;
		return num;
	}
	
	public void tick()
	{
		
	}
	
	// public void render(Graphics g)
	// {
	// 	g.setColor(bgColor);
	// 	g.drawRect(x, y, width, height);
		
	// 	//here, your gonna have to to use junky spacing code
	// }
	
	public void render(Graphics g)
	{
//		Graphics g2 = g;
		// Graphics2D g2 = (Graphics2D)g;
		// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		
		// System.out.println("pressed: "+pressed);
		
		//draw the colored button with a edge
		if(!pressed)
		{
			g.setColor(bgColor);
		}
		else
		{
			//lighten/darken by amount
			int amount = 50;
			int rC = limit(bgColor.getRed() + amount);
			int gC = limit(bgColor.getGreen() + amount);
			int bC = limit(bgColor.getBlue() + amount);
			
			//make it light blue
			// int rC = 30;
			// int gC = 100;
			// int bC = 250;
			
			g.setColor(new Color(rC, gC, bC));
		}
		
		g.fillRect(x, y, width, height);
		g.setColor(fgColor);					//note: may want to change to only be black
		g.drawRect(x, y, width, height);
		
		//To do: ideally, the font size will be set to fit inside the button
		Font f = new Font("Verdana", Font.BOLD, 28/Main.pixelSize);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		Rectangle2D rect = fm.getStringBounds(text, g);
		
		int xCenter = x + (width/2);
		int yCenter = y + (height/2);
		
		int sX = xCenter - (int)(rect.getWidth()/2);
		int sY = yCenter - (int)(rect.getHeight()/2) + fm.getAscent();
		
		g.drawString(text, sX, sY);
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof Button)
		{
			Button b = (Button)obj;
			// return (b.x == x && b.y == y && b.width == width && b.height == height && b.text.equals(text));
			return (b.id == this.id);
		}
		return false;
	}
}