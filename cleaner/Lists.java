package cleaner;

import java.io.File;
import java.util.ArrayList;

public class Lists
{
	public static File listsDir = new File("lists/");
	public static File logPath = new File("lists/Log.txt");
	public static MatchList extensions = new MatchList();	//these file extensions will be blacklisted
	public static MatchList blacklist = new MatchList();	//these files will be blacklisted
	public static MatchList whitelist = new MatchList();	//these files will be whitelisted
	public static MatchList blacklistFolders = new MatchList();	//files in these folders will be blacklisted
	public static MatchList cacheFolders = new MatchList();		//contains copies of recent files (files here will be checked)
	public static MatchList historyFolders = new MatchList();	//contains links to recent files (files here will be checked)
	
	public static String BLACKLIST = "Blacklist";
	public static String WHITELIST = "Whitelist";
	public static String EXTENSION = "Extension";
	public static String BLACKLISTFOLDER = "Folder";
	public static String HISTORY = "History";
	public static String CACHE = "Cache";
	public static String UNKNOWN = "Uknown";
	
	public static boolean saveToLog = true;
	
	public static void main(String[] args)
	{
		loadLists();
		
//		for(String str : blacklist.getContents())
//			System.out.println(str);
		
		ArrayList<Match> cacheMatches = getMatches(cacheFolders, true);
		for(Match match : cacheMatches)
			if(match.isBlacklisted)
				System.out.println(match);
		
		ArrayList<Match> historyMatches = getMatches(historyFolders, true);
		for(Match match : historyMatches)
			if(match.isBlacklisted)
				System.out.println(match);
	}
	
	public static ArrayList<Match> getMatches(MatchList folders, boolean checkSubFolders)
	{
		ArrayList<Match> matches = new ArrayList<Match>();
		for(String str : folders.getUnrestrictedContents())		//for each folder path to check
		{
			ArrayList<File> files = new ArrayList<File>();
			if(checkSubFolders)
					files = SaveNLoad.getFilesRecur(new File(addSpecialFolders(str)));	//check subfolders
			else
				files = SaveNLoad.getFiles(new File(addSpecialFolders(str)));			//check outermost files
			
			for(File file : files)	//get all the files in that folder
			{
				Match match = getMatch(file.getAbsolutePath());
				if(!match.isNull())								//if the file was matched
					matches.add(match);							//add it to the output
			}
		}
		
		if(saveToLog)
		{
			ArrayList<String> logStr = new ArrayList<String>();
			
			for(int i = 0; i < matches.size(); i++)
			{
				Match match = matches.get(i);
				if(match.isBlacklisted)
				{
					logStr.add(match.toString());
				}
			}
			
			if(matches.size() > 0)
			{
				SaveNLoad.addArrayListToFile(logStr, logPath.getAbsolutePath());
			}
		}
		
		return matches;
	}
	
	//check all the files in the lists directory, and add them to their corresponding MatchLists
	public static void loadLists()
	{
		ArrayList<File> files = SaveNLoad.getFilesRecur(listsDir);
		for(File file : files)
		{
			String name = file.getName();
			if(getFileType(name).equals(BLACKLIST))
				blacklist.addFile(file, 2);
			else if(getFileType(name).equals(WHITELIST))
				whitelist.addFile(file, 2);
			else if(getFileType(name).equals(EXTENSION))
				extensions.addFile(file, 1);
			else if(getFileType(name).equals(BLACKLISTFOLDER))
				blacklistFolders.addFile(file, 3);
			else if(getFileType(name).equals(HISTORY))
				historyFolders.addFile(file, 3);
			else if(getFileType(name).equals(CACHE))
				cacheFolders.addFile(file, 3);
//			else
//				System.out.println("Unknown   ? " + name.substring(0, name.length()-cleanEString(name).length()) );
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
		else if(str.endsWith(BLACKLISTFOLDER+".txt"))
			return BLACKLISTFOLDER;
		else if(str.endsWith(HISTORY+".txt"))
			return HISTORY;
		else if(str.endsWith(CACHE+".txt"))
			return CACHE;
		else
			return UNKNOWN;
	}
	
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
	
	public static boolean shouldMarkFile(String str)
	{
		return !whitelist.hasMatch(cleanString(str)) && (extensions.hasMatch(cleanEString(str)) || 
				blacklistFolders.hasMatch(cleanFString(str)) || blacklist.hasMatch(cleanString(str)) );
	}
	
	public static Match getMatch(String str)
	{
		String whitelistMatch = whitelist.getMatch(cleanString(str));
		if(whitelistMatch.length() > 0)
			return new Match(str, whitelistMatch, false);
		
		String extensionsMatch = extensions.getMatch(cleanEString(str));
		if(extensionsMatch.length() > 0)
			return new Match(str, extensionsMatch, true);
		
		String blacklistFoldersMatch = blacklistFolders.getMatch(cleanFString(str));
		if(blacklistFoldersMatch.length() > 0)
			return new Match(str, blacklistFoldersMatch, true);
		
		String blacklistMatch = blacklist.getMatch(cleanString(str));
		if(blacklistMatch.length() > 0)
			return new Match(str, blacklistMatch, true);
		
		return new Match(str, "", false);
	}
}