package menusystem2;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.awt.Graphics;

import main.ButtonChecker;

public class Menu
{
	public static long maxID = 0;
	public long ID = -1;			//id of the menu
	
	public static int indentation = 0;		//used in the printMenus method
	
	public boolean placeholder = false, unsized = false;	//unsized = pixel dimensions not yet set
	
	public ArrayList<Menu> menus = new ArrayList<Menu>();
	
	public double x = 0, y = 0;
	public int width = 0, height = 0;	//dimensions of menu in pixels
	public int xPadding = 10, yPadding = 10;		//extra pixels between each row and column
	
	public int rows = 1, cols = 1;					//number of rows and columns in menu
	public int xPos = 0, yPos = 0, xSize = 1, ySize = 1;	//dimension in "button units"
	
	public Color bgcolor = new Color(255, 0, 255);	//background color of menu
	
	public boolean pressed1 = false, pressed2 = false, pressed3 = false;	//true if mouse is in menu and button is pressed 
	
	//a placeholder menu
	public Menu()
	{
		placeholder = true;
		unsized = true;
	}
	
	//a menu without pixel dimensions so they can be set by the upper menu (bool required so as not to conflict with other constructor)
	public Menu(int xPos, int yPos, int xSize, int ySize, boolean bool)
	{
		assignID();
		unsized = true;
		this.xPos = xPos;
		this.yPos = yPos;
		this.xSize = xSize;
		this.ySize = ySize;
		setColor(new Color(random(0,255), random(0, 255), random(0,255)));
	}
	
	public Menu(int x, int y, int width, int height)
	{
		assignID();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setColor(new Color(random(0,255), random(0, 255), random(0,255)));
	}
	
	public void assignID()
	{
		ID = maxID;
		maxID++;
		ButtonChecker.addID();
	}
	
	public void setRowsCols(int rows, int cols)
	{
		this.rows = rows;
		this.cols = cols;
	}
	
	//set the background color of the menu
	public void setColor(Color color)
	{
		bgcolor = new Color(color.getRed(), color.getGreen(), color.getBlue());
	}
	
	// generate random number in range [min, max]
	public static int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public void addMenu(Menu menu)
	{
		if(menu.xSize > menu.xPos+this.cols || menu.ySize > menu.yPos+this.rows)
		{
			System.err.println("Tried to add a button that was too big!");
			return;
		}
		
		if(menu.unsized)
		{
			menu.x = this.x + (int)(this.width  * (1.0 * menu.xPos  / this.cols));
			menu.y = this.y + (int)(this.height * (1.0 * menu.yPos  / this.rows));
			menu.width =      (int)(this.width  * (1.0 * menu.xSize / this.cols));
			menu.height =     (int)(this.height * (1.0 * menu.ySize / this.rows));
		}
		
		//apply edge
		menu.x += xPadding;
		menu.y += yPadding;
		menu.width -= xPadding*2;
		menu.height -= yPadding*2;
		
		menus.add(menu);
	}
	
	public void fillMenu()
	{
		int num = 1;		//for the button labels
		
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				if(!isOccupied(row, col))
				{
					addMenu(new Button(col, row, 1, 1, true, "Box "+num));
					num++;
				}
			}
		}
	}
	
	public boolean isOccupied(int row, int col)
	{
		if(menus.size() == 0)
			return false;
		
		for(int i = 0; i < menus.size(); i++)
		{
			Menu menu = menus.get(i);
			if((col >= menu.xPos && col < menu.xPos+menu.xSize) && (row >= menu.yPos && row < menu.yPos+menu.ySize))
				return true;
		}
		return false;
	}
	
	public boolean contains(Point p)
	{
		return new Rectangle((int)x, (int)y, width, height).contains(p);
	}
	
	//return most deeply nested menu at point
	public Menu getSubMenu(Point p)
	{
		for(int i = 0; i < menus.size(); i++)
		{
			Menu m = menus.get(i);
			if(m.contains(p))
				return m.getSubMenu(p);
		}
		return this;	//will get call only when menus' size is 0
	}
	
	//return 1st level menu at that point
	public Menu getMenu(Point p)
	{
		for(int i = 0; i < menus.size(); i++)
		{
			Menu m = menus.get(i);
			if(m.contains(p))
				return m;
		}
		return new Menu();	//returns menu with boolean placeholder set to true
	}
	
	public void press1(Point p, boolean beingPressed)
	{
		if(contains(p))
		{
//			System.out.println(this);
			
			pressed1 = beingPressed;
			ButtonChecker.pressed1(this);
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).press1(p, beingPressed);
		}
		else
		{
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).press1(p, false);
			pressed1 = false;
			ButtonChecker.pressed1(this);
		}
	}
	
	public void press2(Point p, boolean beingPressed)
	{
		if(contains(p))
		{
//			System.out.println(this);
			
			pressed2 = beingPressed;
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).press2(p, beingPressed);
		}
		else
		{
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).press2(p, false);
			pressed2 = false;
		}
	}
	
	public void press3(Point p, boolean beingPressed)
	{
		if(contains(p))
		{
//			System.out.println(this);
			
			pressed3 = beingPressed;
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).press3(p, beingPressed);
		}
		else
		{
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).press3(p, false);
			pressed3 = false;
		}
	}
	
	//returns true if this menu actually scrolled something
	public boolean scroll(Point p, boolean scrollingUp)
	{
		if(contains(p))
			for(int i = 0; i < menus.size(); i++)
				menus.get(i).scroll(p, scrollingUp);
		return false;
	}
	
	public void printMenus()
	{
		System.out.println(this);
		
		boolean indent = menus.size() > 0;
		if(indent)
			indentation++;
		
		for(int i = 0; i < menus.size(); i++)
		{
			if(indent)
				for(int j = 0; j < indentation; j++)
					System.out.print("| ");
			menus.get(i).printMenus();
		}
		
		if(indent)
			indentation--;
	}
	
	public void tick()
	{
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).tick();
	}
	
	public void render(Graphics g)
	{
		g.setColor(bgcolor);
		g.fillRect((int)x, (int)y, width, height);
		
		for(int i = 0; i < menus.size(); i++)
			menus.get(i).render(g);
	}
	
	public String toString()
	{
		return "Menu("+rows+", "+cols+") ID: "+ID;
	}
}
