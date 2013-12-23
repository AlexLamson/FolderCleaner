package menusystem2;

import java.awt.Color;

public class ColorGen
{
	public static Color randomColor()
	{
		return new Color(NumGen.random(0,255), NumGen.random(0,255), NumGen.random(0,255));
//		return new Color(random(2,13), random(40,105), random(83,171));
		
//		float hbase = 0.604f, hspread = .01f;
//		float sbase = 0.73f, sspread = .05f;
//		float bbase = 0.19f, bspread = .10f;
//		
//		float h = randomf(hbase-hspread, hbase+hspread);
//		float s = randomf(sbase-sspread, sbase+sspread);
//		float b = randomf(bbase-bspread, bbase+bspread);
////		float h = 0.56f;
////		float s = .5f;
////		float b = .5f;
//		return Color.getHSBColor(h, s, b);
	}
	
	//positive amount = brighter, negative = darker
	public static Color changeColor(Color color, int amount)
	{
		return makeColor(color.getRed()+amount, color.getGreen()+amount, color.getBlue()+amount);
	}
	
	public static Color invertColor(Color color)
	{
		return new Color(255-color.getRed(), 255-color.getGreen(), 255-color.getBlue());
	}
	
	public static Color makeColor(int r, int g, int b)
	{
		r = capRange(r);
		g = capRange(g);
		b = capRange(b);
		return new Color(r, g, b);
	}
	
	public static int capRange(int num)
	{
		if(num < 0)
			num = 0;
		else if(num > 255)
			num = 255;
		return num;
	}
}
