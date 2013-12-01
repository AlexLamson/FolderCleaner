package cleaner;

import java.io.File;
import java.util.ArrayList;

public class Lists
{
	public static File listsDir = new File("lists/");
	public static MatchList extensions = new MatchList();
	public static MatchList blacklist = new MatchList();
	public static MatchList whitelist = new MatchList();
	public static MatchList cacheFolders = new MatchList();
	public static MatchList historyFolders = new MatchList();
	
	public static String BLACKLIST = "Blacklist";
	public static String WHITELIST = "Whitelist";
	public static String EXTENSION = "Extension";
	public static String HISTORY = "History";
	public static String CACHE = "Cache";
	public static String UNKNOWN = "Uknown";
	
	public static void main(String[] args)
	{
		loadLists();
		
//		for(String str : blacklist.getContents())
//			System.out.println("~"+str);
	}
	
	//check all the files in the lists directory, and add them to their corresponding MatchLists
	public static void loadLists()
	{
		ArrayList<File> files = SaveNLoad.getFilesRecur(listsDir);
		for(File file : files)
		{
			String name = file.getName();
			if(getFileType(name).equals(BLACKLIST))
				blacklist.addFile(file);
			else if(getFileType(name).equals(WHITELIST))
				whitelist.addFile(file);
			else if(getFileType(name).equals(EXTENSION))
				extensions.addFile(file);
			else if(getFileType(name).equals(HISTORY))
				historyFolders.addFile(file);
			else if(getFileType(name).equals(CACHE))
				cacheFolders.addFile(file);
			else
				System.out.println("Unknown   ? " + name.substring(0, name.length()-cleanEString(name).length()) );
		}
	}
	
	public static String getFileType(String str)
	{
		if(str.endsWith(BLACKLIST+".txt"))
			return BLACKLIST;
		else if(str.endsWith(WHITELIST+".txt"))
			return WHITELIST;
		else if(str.endsWith(EXTENSION+".txt"))
			return EXTENSION;
		else if(str.endsWith(HISTORY+".txt"))
			return HISTORY;
		else if(str.endsWith(CACHE+".txt"))
			return CACHE;
		else
			return UNKNOWN;
	}
	
	//change to lowercase & replace all special characters with spaces (except last period)
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
		String newStr = new String(newString).trim();
		
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
	
	public static boolean shouldMarkFile(String str)
	{
		return !whitelist.hasMatch(cleanString(str)) && (extensions.hasMatch(cleanEString(str)) || 
				historyFolders.hasMatch(cleanFString(str)) || cacheFolders.hasMatch(cleanFString(str)) || 
				blacklist.hasMatch(cleanString(str)) );
	}
}