package cleaner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class Test
{
	public static final String desktop = SaveNLoad.getDesktop();
	
	public static void main(String[] args)
	{
		
	}
	
	public static void killProcess(String process)
	{
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im "+process+".exe");
		} catch (Exception e){}
	}
}