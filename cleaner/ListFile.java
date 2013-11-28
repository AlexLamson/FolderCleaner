package cleaner;

import java.io.File;
import java.util.ArrayList;

public class ListFile
{
	protected File listPath;	//path to file
	protected ArrayList<String> listContents = new ArrayList<String>();	//contents of file
	protected Boolean useList = new Boolean();	//boolean whether to use file
	
	public ListFile(File file)
	{
		listPath = file;
		loadConetents();
	}
	
	public ListFile(String path)
	{
		this(new File(path));
	}
	
	public void loadConetents()
	{
		String[] contentsArray = SaveNLoad.fileToArray(listPath.getAbsolutePath());
		for(String str : contentsArray)
			if(str.trim().length() > 0)		//if the line has content
				listContents.add(str);
		
		useList.makeTrue();
		
		System.out.println("+ "+listPath.getName());
	}
	
	public ArrayList<String> getUnrestrictedContents()
	{
		if(useList.isTrue())
			return listContents;
		return new ArrayList<String>();
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
}
