package menuSystem;

import java.awt.Color;
import java.awt.Graphics;

public class LoadingBar
{
	public int x, y, width, height;
	public int current, total;
	
	public LoadingBar(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		current = 0;
		total = 100;
	}
	
	public LoadingBar(int x, int y, int width, int height, int total)
	{
		this(x, y, width, height);
		current = 0;
		this.total = total;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.darkGray);
		g.fillRect(x, y, width, height);
		g.setColor(Color.green);
		g.fillRect(x, y, (int)(1.0*width*current/total), height);
	}
}
