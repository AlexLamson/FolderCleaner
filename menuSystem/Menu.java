package menuSystem;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Point;
import java.awt.Color;
import javax.swing.JOptionPane;

public class Menu
{
	public int buttonEdge = 10;			//spacing between buttons

//	public ArrayList<Menu> menu = new ArrayList<Menu>();		//menus should nest
	public ArrayList<Button> buttons = new ArrayList<Button>();
	public Color bgColor;
	public int rows, cols;
	public int x, y, width, height;		//rectangle that represents the menu
	//Note: buttons don't need to fall inside the rectangle of the menu
	
	public Menu()
	{
		bgColor = new Color(220, 220, 220);
		// addButton(new Button(100,100,200,100));		//just as a test
	}
	
	public Menu(int x, int y, int width, int height)
	{
		this();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public Menu(int x, int y, int width, int height, int cols, int rows)
	{
		this(x, y, width, height);
		
		this.rows = rows;
		this.cols = cols;
		
		fillTable();
	}
	
	public void fillTable()
	{
		int bWidth = width/cols;
		int bHeight = height/rows;
		
		int num = 1;		//for the button labels
		
		for(int row = 0; row < rows; row++)
		{
			for(int col = 0; col < cols; col++)
			{
				addButton(new Button(col*bWidth, row*bHeight, bWidth, bHeight, ""+num));
				num++;
			}
		}
	}
	
	public int getButtonWidth()
	{
		return width/cols;
	}
	
	public int getButtonHeight()
	{
		return height/rows;
	}
	
	public void addButton(Button b)
	{
		//apply edge
		b.x += buttonEdge;
		b.y += buttonEdge;
		b.width -= buttonEdge*2;
		b.height -= buttonEdge*2;
		
		buttons.add(b);
	}
	
	public void removeButton(Button b)
	{
		buttons.remove(b);
	}
	
	public boolean containsButton(Button b)
	{
		return buttons.contains(b);
	}
	
	public Button getButton(Point p)
	{
		for(int i = 0; i < buttons.size(); i++)
		{
			Button b = buttons.get(i);
			if(b.contains(p))
				return b;
		}
		return new Button();
	}
	
	public void unpressAll()
	{
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).unpress();
	}
	
	public void click(Point p)
	{
		//set all buttons to unpressed
		unpressAll();
		
		//if a button was pressed, set its to pressed to true
		Button b = getButton(p);
		if(!b.equals(Button.placeholder))
		{
			b.press();
		}
	}
	
	public void rightClick(Point p)
	{
		Button b = getButton(p);
		if(!b.equals(Button.placeholder))
		{
			String input = JOptionPane.showInputDialog ( "Enter a new button title" ); 
			b.text = input+"";
		}
	}
	
	public void tick()
	{
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).tick();
	}
	
	public void render(Graphics g)
	{
		g.fillRect(x, y, width, height);
		
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(g);
	}
}