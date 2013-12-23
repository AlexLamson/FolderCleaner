package menusystem2;

import java.awt.Color;
import java.awt.Graphics;

public class BooleanButton extends Button
{
	public Color bgColorTrue = Color.green;
	public Color bgColorFalse = Color.red;
	
	public String strTrue = "";
	public String strFalse = "";
	
	public boolean boolState = true;
	
	public BooleanButton()
	{
		super();
	}
	
	public BooleanButton(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public BooleanButton(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		super(xPos, yPos, xSize, ySize, bool);
	}
	
	public BooleanButton(int x, int y, int width, int height, String str)
	{
		super(x, y, width, height, str);
		strTrue = str;
		strFalse = str;
	}
	
	public BooleanButton(int xPos, int yPos, int xSize, int ySize, boolean bool, String str)
	{
		super(xPos, yPos, xSize, ySize, bool, str);
		strTrue = str;
		strFalse = str;
	}
	
	public BooleanButton(int x, int y, int width, int height, String strTrue, String strFalse)
	{
		super(x, y, width, height, strTrue);
		this.strTrue = strTrue;
		this.strFalse = strFalse;
	}
	
	public BooleanButton(int xPos, int yPos, int xSize, int ySize, boolean bool, String strTrue, String strFalse)
	{
		super(xPos, yPos, xSize, ySize, bool, strTrue);
		this.strTrue = strTrue;
		this.strFalse = strFalse;
	}
	
	public void clicked1(boolean beingPressed)
	{
		super.clicked1(beingPressed);
		
		if(!beingPressed)
			changeState();
	}
	
	public void changeState()
	{
		boolState = !boolState;
		
		if(boolState)
			str = strTrue;
		else
			str = strFalse;
	}
	
	public void render(Graphics g)
	{
		if(boolState)
			bgcolor = bgColorTrue;
		else
			bgcolor = bgColorFalse;
		fillBackground(g, bgcolor);
		
		Color tColor = this.textColor;
		if(useInvertedText)
			tColor = invertColor(bgcolor);
		drawText(g, str, tColor);
		
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
}
