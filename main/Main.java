package main;
/*
 * Alex's awesome ______ program!
 * 
 * Controls:
 * left click	- ______
 * middle click	- ______
 * right click	- ______
 * space		- ______
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
	
	public static int pixelSize = 1;			//change the scale the pixels are multiplied by when drawn to
	
	public static int computerSpeed = 10;		//higher number for slower computers
	public static int tickTime = 5;
	public static boolean isRunning = false;
	
	public static String windowName = "Menu Test";
	
	public static boolean debugMode = true;
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int screenWidth = (int)screenSize.getWidth();
	public static int screenHeight = (int)screenSize.getHeight();
	public static Dimension realSize;															//size of whole window
	public static Dimension size = new Dimension(screenWidth*2/3,screenHeight*2/3);				//drawable area
	public static Dimension pixel = new Dimension(size.width/pixelSize, size.height/pixelSize);	//"pixels" in drawable area
	
	public static Point mse = new Point(0, 0);
	
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
		menu.setRowsCols(6, 10);
		
		menu.fillMenu();
		
		menu.setColor(Color.darkGray);
		
//		blue loader
		Loader loader = new Loader(1, 2, 5, 1, true);
		loader.fgcolor = new Color(2, 105, 171);
		loader.bgcolor = new Color(13, 40, 83);
		menu.addMenu(loader);
		
//		green loader
		Loader loader2 = new Loader(0, 0, 6, 1, true);
		loader2.fgcolor = new Color(6, 176, 37);
		loader2.bgcolor = new Color(230, 230, 230);
		menu.addMenu(loader2);
		
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
	}

	public void render()
	{
		Graphics g = screen.getGraphics();

//		g.setColor(new Color(0, 150, 255));
//		g.fillRect(0, 0, pixel.width, pixel.height);
		
		
		//call render methods here
		menu.render(g);
		
//		g.setColor(Color.red);
//		g.drawLine(0, 0, pixel.width, pixel.height);
//		g.setColor(Color.green);
//		g.drawLine(0, pixel.height, pixel.width, 0);
		
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
				Thread.sleep(tickTime*(int)computerSpeed);
			}catch(Exception e){ }
		}
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		
		frame = new JFrame();
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