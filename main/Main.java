package main;
/*
 * Folder Cleaner:
 * Remove links or other files with blacklists and whitelists
 * 
 * Controls:
 * Left Click - everything
 * Scroll - scroll
 */

import java.applet.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.*;

import menusystem2.*;

public class Main extends Applet implements Runnable
{
	private static final long serialVersionUID = 8864158495101925325L;				//because stupid warnings
	
	public static int pixelSize = 1;		//change the scale the pixels are multiplied by when drawn to
	
	public static int tickTime = 50;		//milliseconds between each tick
	public static boolean isRunning = false;
	
	public static String windowName = "Folder Cleaner";
	
	public static boolean debugMode = true;
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenWidth = (int)screenSize.getWidth();
	public static int screenHeight = (int)screenSize.getHeight();
	public static Dimension realSize;															//size of whole window
	public static Dimension size = new Dimension(screenWidth*2/3,screenHeight*2/3);				//drawable area
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);	//"pixels" in drawable area
	
	public static Point mse = new Point(0, 0);
	public static Point prevmse = new Point(0, 0);
	public static boolean isDragging = false;
	public static boolean isMouseLeft = false;
	public static boolean isMouseMiddle = false;
	public static boolean isMouseRight = false;
	
	private Image screen;
	public static JFrame frame;
	//put special objects here
	public static Menu menu;
	
	public Main()
	{
		setPreferredSize(size);
	}
	
	public static void restart()
	{
		Main main = new Main();
		main.start();
	}
	
	public void start()
	{
		//defining objects
		menu = new Menu(0, 0, pixel.width, pixel.height);
		
		if(MakeMenu.useTestMenu)
		{
			menu = MakeMenu.makeTestMenu(menu);
		}
		else
		{
			menu = MakeMenu.makeMenu(menu);
			Updater.startUp();
		}
		
		addKeyListener(new Listening());
		addMouseListener(new Listening());
		addMouseMotionListener(new Listening());
		addMouseWheelListener(new Listening());
		
		//start the main loop
		isRunning = true;
		new Thread(this).start();
		requestFocus();
	}

	public void stop()
	{
		isRunning = false;
	}

	public void tick()
	{
//		if(frame.getWidth() != realSize.width || frame.getHeight() != realSize.height)
//			frame.pack();
		
		//call tick methods here
		menu.tick();
		
		Menu hoverMenu = Main.menu.getSubMenu(Main.mse);
		hoverMenu.hover = true;
		
	}

	public void render()
	{
		Graphics g = screen.getGraphics();

//		g.setColor(new Color(0, 150, 255));
//		g.fillRect(0, 0, pixel.width, pixel.height);
		
		
		//call render methods here
		menu.render(g);
		
		//testing that dragging works
//		if(Main.debugMode)
//		{
//			if(isDragging)
//			{
//				g.setColor(Color.green);
//				g.drawLine(prevmse.x, prevmse.y, mse.x, mse.y);
//			}
//		}
		
//		g.setColor(Color.red);
//		g.drawLine(pixel.width/2, 0, pixel.width/2, pixel.height);
//		g.drawLine(0, pixel.height/2, pixel.width, pixel.height/2);
		
		g = getGraphics();

		g.drawImage(screen, 0, 0, size.width, size.height, 0, 0, pixel.width, pixel.height, null);
		g.dispose();		//throw it away to avoid lag from too many graphics objects
	}

	public void run()
	{
		screen = createVolatileImage(pixel.width, pixel.height);	//actually use the graphics card (less lag)
		
		// render();
		// JOptionPane.showMessageDialog(null, "Controls:\n\nWho knows!?");
		
		while(isRunning)
		{
			tick();			//do math and any calculations
			render();		//draw the objects

			try
			{
				Thread.sleep(tickTime);
			}catch(Exception e){ }
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		
		frame = new JFrame();
		
//		Main.frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
//		Main.frame.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		frame.add(main);
		frame.pack();
		
		realSize = new Dimension(frame.getWidth(), frame.getHeight());
		
		frame.setTitle(windowName);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);		//null makes it go to the center
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		main.start();
	}
}