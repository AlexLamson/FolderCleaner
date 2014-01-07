package cleaner;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import main.Updater;

public class Lists
{
	public static String BLACKLIST = "Blacklist";
	public static String WHITELIST = "Whitelist";
	public static String EXTENSION = "Extension";
	public static String BLACKLISTFOLDER = "BFolder";
	public static String WHITELISTFOLDER = "WFolder";
	public static String HISTORY = "History";
	public static String CACHE = "Cache";
	public static String LOG = "Log";
	public static String UNKNOWN = "Uknown";
	
	public static File listsDir = new File("lists/");
	public static File logPath = new File("lists/"+LOG+".txt");
	public static MatchList extensions = new MatchList();	//these file extensions will be blacklisted
	public static MatchList blacklist = new MatchList();	//these files will be blacklisted
	public static MatchList whitelist = new MatchList();	//these files will be whitelisted
	public static MatchList blacklistFolders = new MatchList();	//files in these folders will be blacklisted
	public static MatchList whitelistFolders = new MatchList();	//files in these folders will be whitelisted
	public static MatchList cacheFolders = new MatchList();		//contains copies of recent files (files here will be checked)
	public static MatchList historyFolders = new MatchList();	//contains links to recent files (files here will be checked)
	
	public static boolean saveToLog = true;
	public static boolean deleteMatchedFiles = false;
	
	public static void main(String[] args)
	{
		loadLists();
		
//		for(String str : blacklist.getContents())
//			System.out.println(str);
		
//		ArrayList<Match> cacheMatches = getMatches(cacheFolders, true);
//		for(Match match : cacheMatches)
//			if(match.isBlacklisted)
//				System.out.println(match);
		
		ArrayList<Match> historyMatches = getMatches(historyFolders, true);
		for(Match match : historyMatches)
			if(match.isBlacklisted)
				System.out.println(match.toStringmaxChars(StringParser.getMaxChars(historyMatches)));
	}
	
	public static ArrayList<Match> getMatches(MatchList folders, boolean checkSubFolders)
	{
		long startTime = System.currentTimeMillis();
		
		ArrayList<Match> matches = new ArrayList<Match>();
		for(String str : folders.getUnrestrictedContents())		//for each folder path to check
		{
			ArrayList<File> files = new ArrayList<File>();
			if(checkSubFolders)
				files = SaveNLoad.getFilesRecur(new File(StringParser.addSpecialFolders(str)));	//check subfolders
			else
				files = SaveNLoad.getFiles(new File(StringParser.addSpecialFolders(str)));		//check outermost files
			
			Updater.setTotalFiles(files.size());
			
			for(int i = 0; i < files.size(); i++)	//for all the files in that folder
			{
				files.set(i, new File(StringParser.parseShortcut(files.get(i).getAbsolutePath())));
				
				Match match = getMatch(files.get(i));
				if(!match.isNull())			//if the file was matched
					matches.add(match);		//add it to the output
				
				Updater.incrementScannedFiles();
			}
		}
		
		//save to the log file
		if(saveToLog)
		{
			ArrayList<String> logStr = new ArrayList<String>();
			
			if(matches.size() > 0)	//if there were matches
			{
				//add the date, time and duration to the top
				String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
				String duration = ((System.currentTimeMillis()-startTime))+" ms";
				logStr.add(date+" "+duration);
				
				//add a row of '~'
				String line = "";
				for(int i = 0; i < date.length()+duration.length()+1; i++)
					line += '~';
				logStr.add(line);
				
				//add the list of relevant matches
				int maxChars = StringParser.getMaxChars(matches);
				for(int i = 0; i < matches.size(); i++)	//for all the matches
				{
					Match match = matches.get(i);
					if(match.isBlacklisted)				//if the match was interesting
						logStr.add(match.toStringmaxChars(maxChars));	//add it to the list
				}
				
				logStr.add("");

				
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
			else if(getFileType(name).equals(WHITELISTFOLDER))
				whitelistFolders.addFile(file, 0);
			else if(getFileType(name).equals(BLACKLISTFOLDER))
				blacklistFolders.addFile(file, 0);
			else if(getFileType(name).equals(HISTORY))
				historyFolders.addFile(file, 3);
			else if(getFileType(name).equals(CACHE))
				cacheFolders.addFile(file, 3);
			else if(getFileType(name).equals(LOG))
				continue;
			else
				System.err.println("Unknown type \"" + name.substring(0, name.length()) +"\"" );
		}
		
		Updater.addLists();
		Updater.addRecent();
	}
	
	public static String getFileType(String str)
	{
		if(str.endsWith(BLACKLIST+".txt"))
			return BLACKLIST;
		else if(str.endsWith(WHITELIST+".txt"))
			return WHITELIST;
		else if(str.endsWith(EXTENSION+".txt"))
			return EXTENSION;
		else if(str.endsWith(WHITELISTFOLDER+".txt"))
			return WHITELISTFOLDER;
		else if(str.endsWith(BLACKLISTFOLDER+".txt"))
			return BLACKLISTFOLDER;
		else if(str.endsWith(HISTORY+".txt"))
			return HISTORY;
		else if(str.endsWith(CACHE+".txt"))
			return CACHE;
		else if(str.endsWith(LOG+".txt"))
				return LOG;
		else
			return UNKNOWN;
	}
	
	public static boolean shouldMarkFile(String str)
	{
		return !whitelistFolders.hasMatch(str) && !whitelist.hasMatch(StringParser.cleanString(str)) && 
				(extensions.hasMatch(StringParser.cleanEString(str)) || blacklistFolders.hasMatch(str) || 
				blacklist.hasMatch(StringParser.cleanString(str)) );
	}
	
	public static Match getMatch(File file)
	{
		String str = file.getAbsolutePath();
		
		Match whitelistFoldersMatch = whitelistFolders.getMatch(file, str, false);
		if(whitelistFoldersMatch.matchedTerm.length() > 0)
			return whitelistFoldersMatch;
		
		Match whitelistMatch = whitelist.getMatch(file, StringParser.cleanString(str), false);
		if(whitelistMatch.matchedTerm.length() > 0)
			return whitelistMatch;
		
		Match extensionsMatch = extensions.getMatch(file, StringParser.cleanEString(str), true);
		if(extensionsMatch.matchedTerm.length() > 0)
			return extensionsMatch;
		
		Match blacklistFoldersMatch = blacklistFolders.getMatch(file, str, true);
		if(blacklistFoldersMatch.matchedTerm.length() > 0)
			return blacklistFoldersMatch;
		
		Match blacklistMatch = blacklist.getMatch(file, StringParser.cleanString(str), true);
		if(blacklistMatch.matchedTerm.length() > 0)
			return blacklistMatch;
		
		return new Match(file, new File("null"), "", false);
	}
}