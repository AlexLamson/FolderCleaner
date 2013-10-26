package menuSystem;

//import java.awt.Point;

public class ComplexMenu extends Menu
{
	public ComplexMenu()
	{
		super();
	}
	
	public ComplexMenu(int x, int y, int width, int height)
	{
		super(x, y, width, height);
	}
	
	public ComplexMenu(int x, int y, int width, int height, int cols, int rows)
	{
		super(x, y, width, height);
		
		this.rows = rows;
		this.cols = cols;
		
		// fillTable();
	}
	
	//uses tiles instead of pixels for ints
	public void addButton(int xPos, int yPos, int width, int height)
	{
		addButton(new Button(xPos*getButtonWidth(), yPos*getButtonHeight(), width*getButtonWidth(), height*getButtonHeight()));
	}
	
	public void addButton(int xPos, int yPos, int width, int height, String str)
	{
		addButton(new Button(xPos*getButtonWidth(), yPos*getButtonHeight(), width*getButtonWidth(), height*getButtonHeight(), str));
	}
	
	public void tick()
	{
		// checkForPressed();
	}
	
	public void checkForPressed()
	{
		for(int i = 0; i < buttons.size(); i++)
		{
			Button b = buttons.get(i);
			if(b.pressed)
				checkButton(b.id);
		}
	}
	
	public void checkButton(int id)
	{
		switch(id)
		{
			case 0:
				System.out.println("zero");
				break;
			case 1:
				System.out.println("one");
				break;
			case 2:
				System.out.println("two");
				break;
			case 3:
				System.out.println("three");
				break;
			case 4:
				System.out.println("four");
				break;
			case 5:
				System.out.println("five");
				break;
			case 6:
				System.out.println("six");
				break;
			case 7:
				System.out.println("seven");
				break;
			case 8:
				System.out.println("eight");
				break;
		}
	}

}
