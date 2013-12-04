package cleaner;

//import java.io.File;
//import java.io.IOException;

public class Test
{
	public static final String desktop = SaveNLoad.getDesktop();
	
	public static void main(String[] args)
	{
		System.out.println(addSpecialFolders("~user~cats"));
		System.out.println(addSpecialFolders("~user~/cats"));
	}
	
	public static void killProcess(String process)
	{
		try
		{
			Runtime.getRuntime().exec("taskkill /f /im "+process+".exe");
		} catch (Exception e){}
	}
	
	public static String addSpecialFolders(String str)
	{
		String userString = System.getProperty("user.home").replaceAll("\\\\", "/")+'/';
		str = str.replaceFirst("~user~/", userString);
		str = str.replaceFirst("~user~", userString);
		return str;
	}
}