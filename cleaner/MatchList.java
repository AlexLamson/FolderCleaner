package cleaner;

import java.io.File;
import java.util.ArrayList;

public class MatchList
{
	public boolean useMatchList = true;
	private ArrayList<File> listPaths = new ArrayList<File>();	//path to each file
	private ArrayList<ArrayList<String>> listContents = new ArrayList<ArrayList<String>>();	//contents of each file
	private ArrayList<Boolean> useLists = new ArrayList<Boolean>();	//boolean whether to use file
	
	public MatchList()
	{
		
	}
	
	public void addList(File path)
	{
		listPaths.add(path);
		
		String[] contentsArray = SaveNLoad.fileToArray(path.getAbsolutePath());
		ArrayList<String> contents = new ArrayList<String>();
		for(String str : contentsArray)
			contents.add(str);
		listContents.add(contents);
		
		useLists.add(new Boolean(true));
		
		System.out.println("+ "+path.getName());
	}
	
	//returns -1 if not found
	public int getPosition(String path)
	{
		for(int i = 0; i < listPaths.size(); i++)
			if(listPaths.get(i).equals(path))
				return i;
		return -1;
	}
	
	public void removeList(String path)
	{
		removeList(getPosition(path));
	}
	
	public void removeList(int i)
	{
		listPaths.remove(i);
		listContents.remove(i);
		useLists.remove(i);
	}
	
	//returns null if restricted
	public ArrayList<String> getUnrestrictedContents(int i)
	{
		if(useLists.get(i).bool)
			return listContents.get(i);
		return null;
	}
	
	public ArrayList<String> getContents(int i)
	{
		return listContents.get(i);
	}
	
	public ArrayList<String> getContents(String path)
	{
		return getContents(getPosition(path));
	}
	
	public ArrayList<String> getAllUnrestictedContents()
	{
		ArrayList<String> allUnrestictedContents = new ArrayList<String>();
		
		for(int i = 0; i < listPaths.size(); i++)
			for(String s : getUnrestrictedContents(i))
				allUnrestictedContents.add(s);
		
		return allUnrestictedContents;
	}
}
