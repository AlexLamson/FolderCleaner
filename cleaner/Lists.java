package cleaner;
import java.io.File;
import java.util.ArrayList;

public class Lists
{
	public static File listsDir = new File("lists/");
	public static MatchList blacklist = new MatchList();
	public static MatchList whitelist = new MatchList();
	public static FolderList folders = new FolderList();
	
	public static void main(String[] args)
	{
		loadLists();
//		for(String str : blackList)
//			System.out.println("~"+str);
		
		for(String str : blacklist.getAllUnrestictedContents())
		{
			System.out.println(str);
		}
		
//		System.out.println("!~OBFUSCATED~!");
//		for(String str : obfuscate(blackList))
//		System.out.println(str);
//		System.out.println("!~UNOBFUSCATED~!");
//		for(String str : Cypher.unobfuscate(blackList))
//			System.out.println(str);
	}
	
	public static void loadLists()
	{
		//check all the files in the lists directory, and add them to their corresponding MatchLists
		
		ArrayList<File> files = SaveNLoad.getFilesRecur(listsDir);
		for(File file : files)
		{
			if(file.getName().endsWith("Blacklist.txt"))
			{
				blacklist.addList(file);
			}
			else if(file.getName().endsWith("Whitelist.txt"))
			{
				whitelist.addList(file);
			}
			else if(file.getName().endsWith("Folders.txt"))
			{
				folders.addList(file);
			}
			else
			{
				System.out.println("X "+file.getName());
			}
		}
	}
	
	public static boolean shouldMarkFile(String str)
	{
		
		
		return false;
	}
}