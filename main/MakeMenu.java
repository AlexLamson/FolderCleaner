package main;

import java.awt.Color;
import menusystem2.*;

public class MakeMenu
{
	public static boolean useTestMenu = false;
	
	public static Menu sidebar = new Menu();
	public static Button cleanButton = new Button();
	public static Button scanButton = new Button();
	public static ScrollerButton clearHistoryButton = new ScrollerButton();
	public static Scroller folderScroller = new Scroller();
	public static Scroller listsScroller = new Scroller();
	public static Menu matchbar = new Menu();
	public static Scroller matchScroller = new Scroller();
	public static Loader loadingbar = new Loader();
	
	public static Menu makeMenu(Menu menu)
	{
		menu.setColor(Color.darkGray);
		menu.setColsRows(3, 1);
		menu.setPadding(0, 0);
		
		sidebar = new Menu(0, 0, 1, 1, true);
		sidebar.bgcolor = new Color(0, 75, 136);
		sidebar.setColsRows(4, 8);
		menu.addMenu(sidebar);
		
		cleanButton = new Button(0, 0, 4, 2, true, "Clean");
		cleanButton.useInvertedText = false;
		cleanButton.bgcolor = new Color(255, 77, 0);
		sidebar.addMenu(cleanButton);
		
		scanButton = new Button(0, 2, 4, 1, true, "Scan");
		scanButton.useInvertedText = false;
		scanButton.bgcolor = new Color(136, 198, 0);
		sidebar.addMenu(scanButton);
		
		clearHistoryButton = new ScrollerButton(0, 3, 4, 1, true, "Remove last "+ScrollerButton.prettyNumString);
		clearHistoryButton.useInvertedText = false;
		clearHistoryButton.bgcolor = new Color(0, 170, 255);
		sidebar.addMenu(clearHistoryButton);
		
		folderScroller = new Scroller(0, 4, 4, 2, true);
		folderScroller.setVisibleRows(3);
		folderScroller.setColsRows(1, 6);
		sidebar.addMenu(folderScroller);
//		folderScroller.fillMenu();
		
		listsScroller = new Scroller(0, 6, 4, 2, true);
		listsScroller.setVisibleRows(3);
		listsScroller.setColsRows(2, 6);
		listsScroller.setPadding(2, 2);
		sidebar.addMenu(listsScroller);
//		listsScroller.fillMenu();
		
		sidebar.fillMenu();
		
		matchbar = new Menu(1, 0, 2, 1, true);
		matchbar.setPadding(0, 0);
		matchbar.setColsRows(10, 10);
		menu.addMenu(matchbar);
		
		matchScroller = new Scroller(0, 0, 10, 9, true);
		matchScroller.bgcolor = new Color(0, 109, 193);
		matchScroller.setPadding(0, 10);
		matchScroller.setColsRows(10, 20);
		matchScroller.setVisibleRows(10);
		matchbar.addMenu(matchScroller);
//		matchScroller.fillMenu();
		
		loadingbar = new Loader(0, 9, 10, 1, true);
		loadingbar.test = false;
		loadingbar.fgcolor = new Color(6, 176, 37);
		loadingbar.bgcolor = new Color(150, 150, 150);
		matchbar.addMenu(loadingbar);
		
		return menu;
	}
	
	public static Menu makeTestMenu(Menu menu)
	{
		menu.setColsRows(10, 6);
		menu.setColor(Color.darkGray);
		
		Button btest = new Button(0, 0, 1, 1, true, "abcdefghijklmnopqrstuvwxyz0123456789");
		btest.fontSize = 18;
		btest.useSetFontSize = true;
		menu.addMenu(btest);
		
//		blue loader
		Loader loader = new Loader(0, 1, 6, 1, true);
		loader.fgcolor = new Color(2, 105, 171);
		loader.bgcolor = new Color(13, 40, 83);
		menu.addMenu(loader);
		
		Scroller scroller = new Scroller(0, 2, 2, 3, true);
		scroller.setVisibleRows(3);
		scroller.setColsRows(1, 10);
		menu.addMenu(scroller);
		scroller.fillMenu();
		
//		green loader
//		Loader loader2 = new Loader(1, 0, 4, 1, true);
//		loader2.fgcolor = new Color(6, 176, 37);
//		loader2.bgcolor = new Color(230, 230, 230);
//		menu.addMenu(loader2);
		
		Button b1 = new Button(4, 2, 3, 1, true, "This is a test button");
		b1.useSetFontSize = true;
		b1.textAlignment = -1;
		menu.addMenu(b1);
		Button b2 = new Button(4, 3, 2, 1, true, "Test button");
		menu.addMenu(b2);
		Button b3 = new Button(4, 4, 2, 1, true, "Test button");
		menu.addMenu(b3);
		Button b4 = new Button(6, 3, 1, 2, true, "Test");
		menu.addMenu(b4);
		
		ScrollerButton sb = new ScrollerButton(2, 2, 2, 2, true, ScrollerButton.prettyNumString+" left!");
		menu.addMenu(sb);
		
		BooleanButton bb = new BooleanButton(0, 5, 1, 1, true, "Green!", "Red!");
		menu.addMenu(bb);
		
		menu.fillMenu();
//		menu.fillMenu(0, 0, menu.cols, menu.rows, 1, 2);
		
		return menu;
	}
}
