package cleaner;

import java.io.File;
import java.util.ArrayList;

public class FolderList extends MatchList
{
	public static final String CACHE = "CACHE", HISTORY = "HISTORY";
		
	protected ArrayList<ArrayList<String>> listTypes = new ArrayList<ArrayList<String>>();	//contents of each file
	protected ArrayList<ArrayList<Boolean>> useContents = new ArrayList<ArrayList<Boolean>>();	//boolean whether to use each path

	public FolderList()
	{
		
	}
	
	//method to return ArrayList of cache folders
	
	//method to return ArrayList of history folders
	
	public void addList(String folderPathString)
	{
		File folderPath = new File(folderPathString);
		super.addFile(folderPath);
		
		// remove the labels from each item from the contents and move them to the listTypes ArrayList
		for(int i = 0; i < listPaths.size(); i++)
		{
			useContents.add(new ArrayList<Boolean>());
			for(int j = 0;  j < listContents.get(i).size(); j++)
			{
				//clean each line
				listContents.get(i).set(j, listContents.get(i).get(j).trim().replaceAll("\\", "/"));
				
				if(listContents.get(i).get(j).startsWith(CACHE))
				{
					listContents.get(i).set(j, listContents.get(i).get(j).substring(CACHE.length()).trim());
					listTypes.get(i).add(CACHE);
					useContents.get(i).add(new Boolean(true));
				}
				else if(listContents.get(i).get(j).startsWith(HISTORY))
				{
					listContents.get(i).set(j, listContents.get(i).get(j).substring(HISTORY.length()).trim());
					listTypes.get(i).add(HISTORY);
					useContents.get(i).add(new Boolean(true));
				}
				else
				{
					//if the path is unlabeled but exists, default to history
					if(new File(listContents.get(i).get(j)).exists())
					{
						listTypes.get(i).add(HISTORY);
						useContents.get(i).add(new Boolean(true));
					}
					else	//otherwise don't use that line
					{
						useContents.get(i).add(new Boolean(false));
						continue;
					}
				}
			}
		}
	}
	
	public void removeList(String path)
	{
		removeList(getPosition(path));
	}
	
	public void removeList(int i)
	{
		super.removeList(i);
		listTypes.remove(i);
		useContents.remove(i);
	}
}
