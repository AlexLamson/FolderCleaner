package cleaner;

import java.io.File;
import java.util.ArrayList;

import menusystem2.Menu;

//MatchList - contains all the files of a certain type (ie blacklist, whitelist, etc)
public class MatchList
{
	public boolean useMatchList = true;
	public ArrayList<ListFile> files = new ArrayList<ListFile>();
	
	public MatchList()
	{
		
	}
	
	public void addFile(File path, int textChange)
	{
		files.add(new ListFile(path, textChange));
//		printFile(path);
	}
	
	//returns -1 if not found
	public int getListFile(String path) throws Exception
	{
		for(int i = 0; i < files.size(); i++)
			if(files.get(i).listPath.equals(path))
				return i;
		throw new Exception("getListFile("+path+") file not found");
	}
	
	public ArrayList<String> getUnrestrictedContents()
	{
		ArrayList<String> allUnrestrictedContents = new ArrayList<String>();
		for(int i = 0; i < files.size(); i++)
			for(String s : files.get(i).getUnrestrictedContents())
				allUnrestrictedContents.add(s);
		return allUnrestrictedContents;
	}
	
	public ArrayList<String> getContents()
	{
		ArrayList<String> allContents = new ArrayList<String>();
		for(int i = 0; i < files.size(); i++)
			for(String s : files.get(i).getContents())
				allContents.add(s);
		return allContents;
	}
	
	//return true if any of the lines in all the enabled files are contained in String str
	public boolean hasMatch(String str)
	{
		if(!useMatchList)
			return false;
		for(int i = 0; i < files.size(); i++)
			for(String match : files.get(i).getUnrestrictedContents())
				if(str.contains(match))
					return true;
		return false;
	}
	
	//returns "" if no match, otherwise return the string that was matched
	public String getMatch(String str)
	{
		if(!useMatchList)
			return "";
		for(int i = 0; i < files.size(); i++)
			for(String match : files.get(i).getUnrestrictedContents())
				if(str.contains(match))
					return match;
		return "";
	}
	
	public static void printFile(File path)
	{
		String type = Lists.getFileType(path.getName());
		String name = path.getName().substring(0, path.getName().length() - (type.length() + 4));
		String spaces = "";
		for(int i = 0; i < 9-type.length(); i++)
			spaces += " ";
		System.out.println( type + spaces + " + " + name );
	}
	
	public ArrayList<Menu> getMenus()
	{
		ArrayList<Menu> menus = new ArrayList<Menu>();
		for(int i = 0; i < files.size(); i++)
			menus.add(files.get(i).toMenu());
		return menus;
	}
}
