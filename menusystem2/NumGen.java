package menusystem2;

public class NumGen
{
	// generate random int in range [min, max]
	public static int random(int min, int max)
	{
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	// generate random float in range [min, max)
	public static float randomf(float min, float max)
	{
		return min + (float)Math.random() * (max - min);
	}
	
	public static double round(double num, int places)
	{
		return (1.0*(int)(num*Math.pow(10, places)))/Math.pow(10, places);
	}
}
