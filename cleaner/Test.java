package cleaner;

//import java.util.ArrayList;
//import java.io.File;
//import java.io.IOException;

public class Test
{
	public static final String desktop = SaveNLoad.getDesktop();
	
	public static void main(String[] args)
	{
		System.out.println("hello world");
	}
	
	public static void killProcess(String process)
	{
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im "+process+".exe");
		} catch (Exception e){}
	}
}