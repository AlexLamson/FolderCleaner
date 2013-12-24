package cleaner;

import java.io.File;
import java.util.ArrayList;

import menusystem2.*;

public class ListFile
{
	protected File listPath;	//path to file
	protected ArrayList<String> listContents = new ArrayList<String>();	//contents of file
	protected Boolean useList = new Boolean();	//boolean whether to use file
	
//	for the int textChange:
//	0 = do nothing
//	1 = change to lowercase
//	2 = 1 & change special chars to spaces
//	3 = remove anything that isn't a valid path
	public ListFile(File file, int textChange)
	{
		listPath = file;
		loadContents();
		switch(textChange)
		{
		case 0:
			break;
		case 1:
			contentsToLowerCase();
			break;
		case 2:
			cleanContents();
			break;
		case 3:
			removeNonPaths();
			break;
		default:
			break;
		}
	}
	
	public ListFile(String path, int textChange)
	{
		this(new File(path), textChange);
	}
	
	public void loadContents()
	{
//		System.out.println(listPath.getAbsolutePath());
		
		String[] contentsArray = SaveNLoad.fileToArray(listPath.getAbsolutePath());
		for(String str : contentsArray)
			if(str.trim().length() > 0)		//if the line has content
				listContents.add(str);		//don't do anything to the string, because it may come from any kind of list
		
		useList.makeTrue();
	}
	
	public void cleanContents()
	{
		for(int i = 0; i < listContents.size(); i++)
			listContents.set(i, StringParser.removeSpecialChars(listContents.get(i)).toLowerCase());
	}
	
	public void contentsToLowerCase()
	{
		for(int i = 0; i < listContents.size(); i++)
			listContents.set(i, listContents.get(i).toLowerCase());
	}
	
	public void removeNonPaths()
	{
		if(listContents.size() > 0)
			for(int i = listContents.size()-1; i > 0; i--)
				if(!new File(listContents.get(i)).exists())
					listContents.remove(i);
	}
	
	public ArrayList<String> getUnrestrictedContents()
	{
		if(useList.isTrue())
			return getContents();
		return new ArrayList<String>();
	}
	
	public ArrayList<String> getContents()
	{
		return listContents;
	}
	
	public boolean equals(Object obj)
	{
		if(obj instanceof ListFile)
			if( ((ListFile) obj).listPath.equals(listPath) )
				return true;
		return false;
	}
	
	public String toString()
	{
		return listPath.toString();
	}

	public Menu toMenu()
	{
		return new BooleanButton(1, 1, true, listPath.getName());
	}
}
