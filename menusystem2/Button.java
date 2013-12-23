package menusystem2;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Button extends Menu
{
	public String str = "Test";
	public boolean useInvertedText = true;	//if false, use textColor
	public Color textColor =  Color.black;
	
	public boolean changeColorWhenClicked = true;
	
	public Button()
	{
		super();
	}
	
	public Button(int x, int y, int width, int height)
	{
		this(x, y, width, height, "");
	}
	
	public Button(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		this(xPos, yPos, xSize, ySize, bool, "");
	}
	
	public Button(int xSize, int ySize, boolean bool)
	{
		super(xSize, ySize, bool);
	}
	
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
	
	public Button(int xSize, int ySize, boolean bool, String str)
	{
		super(xSize, ySize, bool);
		this.str = new String(str);
	}
	
	public int getFontSize(Graphics g)
	{
		int fsize = 8;
		int increment = 1;
		for(int i = 0; i < 72+1; i+=increment)
		{
			Font f = new Font("Verdana", Font.PLAIN, i);
			g.setFont(f);
			Rectangle2D rect = g.getFontMetrics(f).getStringBounds(str, g);
			
			fsize = i-increment;
			if(rect.getMaxX()-rect.getMinX() > width-5)
				break;
			if(rect.getMaxY()-rect.getMinY() > height-5)
				break;
		}
		
		switch(fsize)
		{
		case 12:
			increment = 2;
			break;
		case 28:
			increment = 8;
			break;
		case 36:
			increment = 12;
			break;
		case 48:
			increment = 24;
			break;
		}
		
		return fsize;
	}
	
	public void fillBackground(Graphics g, Color color)
	{
		g.setColor(color);
		g.fillRect((int)x, (int)y, width, height);
	}
	
	public void drawText(Graphics g, String str, Color textColor)
	{
		Font f = new Font("Verdana", Font.PLAIN, getFontSize(g));
		g.setFont(f);
		FontMetrics fm = g.getFontMetrics(f);
		Rectangle2D rect = fm.getStringBounds(str, g);
		
		int xCenter = (int)x + (width/2);
		int yCenter = (int)y + (height/2);
		int sX = xCenter - (int)(rect.getWidth()/2);
		int sY = yCenter - (int)(rect.getHeight()/2) + fm.getAscent();
		
		g.setColor(textColor);
		g.drawString(str, sX, sY);
	}
	
	public void render(Graphics g)
	{
		Color buttonColor = new Color(bgcolor.getRed(), bgcolor.getGreen(), bgcolor.getBlue());
		if(changeColorWhenClicked)
		{
			if(pressed1)
				buttonColor = ColorGen.changeColor(buttonColor, 50);
			if(pressed3)
				buttonColor = ColorGen.changeColor(buttonColor, -50);
		}
		fillBackground(g, buttonColor);
		
		Color tColor = this.textColor;
		if(useInvertedText)
			tColor = ColorGen.invertColor(bgcolor);
		drawText(g, str, tColor);
		
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
	
	public String toString()
	{
		return "Button(\""+str+"\")";
	}
}
