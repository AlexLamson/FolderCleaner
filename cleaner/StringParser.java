package cleaner;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class StringParser
{
	//change all non-alphanumeric characters to spaces
	public static String removeSpecialChars(String str)
	{
		char[] newString = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			if(Character.isLetterOrDigit(c))
				newString[i] = c;
			else
				newString[i] = ' ';
		}
		
		String newStr = new String(newString);
		
		return newStr;
	}
	
	//change to lowercase & replace all special characters with spaces (except last period & first slash)
	public static String cleanString(String str)
	{
//		replace special characters with spaces to avoid bad matches
		boolean foundLastPeriod = false, foundLastSlash = false;
		char[] newString = new char[str.length()];
		for(int i = str.length()-1; i > 0; i--)
		{
			char c = str.charAt(i);
			if(foundLastSlash)
				newString[i] = ' ';
			else if(Character.isLetterOrDigit(c))
				newString[i] = c;
			else
			{
				if(c == '.' && !foundLastPeriod && !foundLastSlash)
				{
					newString[i] = c;
					foundLastPeriod = true;
				}
				else if(c == '/' && !foundLastSlash)
				{
					newString[i] = c;
					foundLastSlash = true;
				}
				else
					newString[i] = ' ';
			}
		}
		String newStr = new String(newString);
		
		newStr = newStr.toLowerCase();
		
		return newStr;
	}
	
	//change string to lowercase (clean folder string)
	public static String cleanFString(String str)
	{
		return str.toLowerCase();
	}
	
	//return characters at and after last period, ex. "C:/hats/tophat.jpg" -> ".jpg" (clean extension string)
	public static String cleanEString(String str)
	{
		int periodLoc = 0;
		for(int i = str.length()-1; i > 0; i--)
		{
			if(str.charAt(i) == '.')
			{
				periodLoc = i;
				break;
			}
		}
		return str.substring(periodLoc).toLowerCase();
	}
	
	public static String parseShortcut(String str)
	{
		if(SaveNLoad.isLink(new File(str)))
		{
			try
			{
				WindowsShortcut ws = new WindowsShortcut(new File(str));
				str = ws.getRealFilename();
			} catch (IOException | ParseException e)
			{
				System.err.println("error parsing shortcut: "+str);
				e.printStackTrace();
			}
		}
		
		String hostname = SaveNLoad.getHostname();
		if(str.startsWith("\\\\"+hostname.toUpperCase()))
			str = "C:\\"+str.substring(hostname.length()+3);
		
		return str;
	}
	
	public static String addSpecialFolders(String str)
	{
		String userString = System.getProperty("user.home").replaceAll("\\\\", "/")+'/';
		
		if(str.startsWith("~"))
		{
			str = str.replaceFirst("~user~/", userString);
			str = str.replaceFirst("~user~", userString);
			
			if(str.startsWith("~?:/"))
			{
				String fileStr = "";
				for(int i = 4; i < str.length(); i++)
				{
					char c = str.charAt(i);
					if(c != '~')
						fileStr += c;
					else
						break;
				}
				
				ArrayList<String> drives = SaveNLoad.getDrives();
				for(String driveStr : drives)
				{
					if(new File(driveStr+fileStr).exists())
					{
						int slash = 0;
						if((str.charAt(5+fileStr.length()) == '/'))
							slash = 1;
						
						driveStr = driveStr.substring(0, 2)+"/";
						str = driveStr+str.substring(5+fileStr.length()+slash, str.length());
						break;
					}
				}
			}
		}
		
		return str;
	}
	
	public static int getMaxChars(ArrayList<Match> matches)
	{
		int maxChars = 0;
		for(Match match : matches)
		{
			if(match.isBlacklisted)
			{
				int size = match.matchedTerm.length();
				if(size > maxChars)
					maxChars = size;
			}
		}
		return maxChars;
	}
}
