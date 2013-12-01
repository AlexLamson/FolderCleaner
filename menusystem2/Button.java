package menusystem2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Main;

public class Button extends Menu
{
	String str = "Test";
	
	public Button(int x, int y, int width, int height, String str)
	{
		super(x, y, width, height);
		this.str = new String(str);
	}
	
	public Button(int xPos, int yPos, int xSize, int ySize, boolean bool, String str)
	{
		super(xPos, yPos, xSize, ySize, bool);
		this.str = new String(str);
	}
	
	//positive amount = brighter, negative = darker
	public Color changeColor(Color color, int amount)
	{
		return makeColor(color.getRed()+amount, color.getGreen()+amount, color.getBlue()+amount);
	}
	
	public Color makeColor(int r, int g, int b)
	{
		r = capRange(r);
		g = capRange(g);
		b = capRange(b);
		return new Color(r, g, b);
	}
	
	public int capRange(int num)
	{
		if(num < 0)
			num = 0;
		else if(num > 255)
			num = 255;
		return num;
	}
	
	public void render(Graphics g)
	{
		if(pressed1)
			g.setColor(changeColor(bgcolor, 50));
		else
			g.setColor(bgcolor);
		g.fillRect(x, y, width, height);
		
		//To do: ideally, the font size will be set to fit inside the button
		Font f = new Font("Verdana", Font.PLAIN, 28/Main.pixelSize);
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		Rectangle2D rect = fm.getStringBounds(str, g);
		
		int xCenter = x + (width/2);
		int yCenter = y + (height/2);
		
		int sX = xCenter - (int)(rect.getWidth()/2);
		int sY = yCenter - (int)(rect.getHeight()/2) + fm.getAscent();

//		g.setColor(Color.black);
		g.setColor(new Color(255-bgcolor.getRed(), 255-bgcolor.getGreen(), 255-bgcolor.getBlue()));
		
		g.drawString(str, sX, sY);
		
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
	
	public String toString()
	{
		return "Button(\""+str+"\")";
	}
}
