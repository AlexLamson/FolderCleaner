package cleaner;

import java.io.File;
import java.util.ArrayList;

public class ListFile
{
	protected File listPath;	//path to file
	protected ArrayList<String> listContent = new ArrayList<String>();	//contents of file
	protected Boolean useList = new Boolean();	//boolean whether to use file
	
	public ListFile(String path)
	{
		listPath = new File(path);
		
	}
}
