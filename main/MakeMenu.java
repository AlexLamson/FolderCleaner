package main;

import java.awt.Color;
import menusystem2.*;

public class MakeMenu
{
	public static Menu makeMenu(Menu menu)
	{
		menu.setRowsCols(1, 4);
		menu.setColor(Color.darkGray);
		
		
		
		return menu;
	}
	
	public static Menu makeTestMenu(Menu menu)
	{
		menu.setRowsCols(6, 10);
		menu.setColor(Color.darkGray);
		
//		blue loader
		Loader loader = new Loader(0, 1, 6, 1, true);
		loader.fgcolor = new Color(2, 105, 171);
		loader.bgcolor = new Color(13, 40, 83);
		menu.addMenu(loader);
		
		Scroller scroller = new Scroller(0, 2, 2, 3, true);
		scroller.setVisibleRows(3);
		scroller.setRowsCols(10, 1);
		menu.addMenu(scroller);
		scroller.fillMenu();
		
////		green loader
//		Loader loader2 = new Loader(1, 0, 4, 1, true);
//		loader2.fgcolor = new Color(6, 176, 37);
//		loader2.bgcolor = new Color(230, 230, 230);
//		menu.addMenu(loader2);
		
		Button b1 = new Button(4, 2, 3, 1, true, "This is a test button");
		menu.addMenu(b1);
		Button b2 = new Button(4, 3, 2, 1, true, "Test button");
		menu.addMenu(b2);
		Button b3 = new Button(4, 4, 2, 1, true, "Test button");
		menu.addMenu(b3);
		Button b4 = new Button(6, 3, 1, 2, true, "Test");
		menu.addMenu(b4);
		
		menu.fillMenu();
		
		return menu;
	}
}
