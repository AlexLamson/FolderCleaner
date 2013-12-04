package menusystem2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Scroller extends Menu
{
	public int pos = 0;	//the currently viewed row
	public int visibleRows = 4;	//the number of rows visible at any one time
	
	public Scroller(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public Scroller(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		super(xPos, yPos, xSize, ySize, bool);
	}
	
	public void setVisibleRows(int visibleRows)
	{
		this.visibleRows = visibleRows;
	}
	
	public void addMenu(Menu menu)
	{
		if(menu.unsized)
		{
			menu.x = this.x + (int)(this.width  * (1.0 * menu.xPos  / this.cols));
			menu.y = this.y + (int)(this.height * (1.0 * menu.yPos  / this.visibleRows));
			menu.width =      (int)(this.width  * (1.0 * menu.xSize / this.cols));
			menu.height =     (int)(this.height * (1.0 * menu.ySize / this.visibleRows));
		}
		
		//apply edge
		menu.x += xPadding;
		menu.y += yPadding;
		menu.width -= xPadding*2;
		menu.height -= yPadding*2;
		
		menus.add(menu);
	}
	
	//returns true if this menu actually scrolled something
	public boolean scroll(Point p, boolean scrollingUp)
	{
		if(contains(p))
		{
			boolean somethingScrolled = false;
			for(int i = 0; i < menus.size(); i++)
			{
				if(menus.get(i).scroll(p, scrollingUp))
				{
					somethingScrolled = true;
					break;
				}
			}
			if(!somethingScrolled)
			{
				scrollMenu(scrollingUp);
				return true;
			}
		}
		return false;
	}
	
	public void scrollMenu(boolean scrollingUp)
	{
		if(scrollingUp && pos > 0)
			changePos(-1);
		else if(!scrollingUp && pos < rows-visibleRows)
			changePos(1);
	}
	
	public void changePos(int direction)
	{
		pos += direction;
		for(int i = 0; i < menus.size(); i++)
		{
			int height = (int)(this.height * (1.0 * menus.get(i).ySize / this.visibleRows));
			menus.get(i).y += -direction*height;
		}
	}
	
	public void render(Graphics g)
	{
		//draw the background
		g.setColor(bgcolor);
		g.fillRect(x, y, width, height);
		
		//draw the submenus
		for(int i = 0; i < menus.size(); i++)
		{
			int yPos = menus.get(i).yPos;
			
			if(yPos < pos+visibleRows && yPos >= pos)
			{
				Menu menu = menus.get(i);
				int height = (int)(this.height * (1.0 * menu.ySize / this.visibleRows));
				menu.render(g);
			}
		}
		
		//draw the scrollbar
		g.setColor(Button.changeColor(bgcolor, 50));
		g.fillRect(x+width-xPadding, y, xPadding, height);
		g.setColor(Button.changeColor(bgcolor, -20));
		g.fillRect(x+width-xPadding, y+(int)(height*(1.0*pos/rows)), xPadding, (int)(height*1.0*visibleRows/rows));
		
		//draw black edging on the scrollbar
		g.setColor(Color.black);
		g.drawLine(x+width-xPadding, y, x+width-xPadding, y+height);
		g.drawRect(x+width-xPadding, y+(int)(height*(1.0*pos/rows)), xPadding, (int)(height*1.0*visibleRows/rows));
	}
}
