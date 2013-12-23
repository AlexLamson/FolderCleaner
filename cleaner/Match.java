package cleaner;

import java.io.File;

public class Match
{
	public File actualFile;		//actual file being checked
	public File linkedFile;		//file being linked to
	public String matchedTerm;				//black/white-listed term (if length == 1 then placeholder match)
	public boolean isBlacklisted = false;	//if false then whitelisted
	
	public Match(File file, String matchedTerm, boolean isBlacklisted)
	{
		this.actualFile = file;
		getLinkedFile();
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isBlacklisted;
	}
	
	public Match(String fileStr, String matchedTerm, boolean isBlacklisted)
	{
		this.actualFile = new File(fileStr);
		getLinkedFile();
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isBlacklisted;
	}
	
	public void getLinkedFile()
	{
		String str = Lists.parseShortcut(actualFile.getAbsolutePath());
		
		if(!str.equals(actualFile.getAbsolutePath()))
		{
			System.out.println(linkedFile.getAbsolutePath());
		}
		
		linkedFile = new File(str);
	}
	
	public boolean isNull()
	{
		return matchedTerm.length() == 0;
	}
	
	public String toStringmaxChars(int maxChars)
	{
		String spaces = "";
		for(int i = 0; i < maxChars-matchedTerm.length()+1; i++)
			spaces += ' ';
		return matchedTerm+spaces+"- "+linkedFile.getAbsolutePath();
	}
	
	public String toString()
	{
		return matchedTerm+"\t- "+linkedFile.getAbsolutePath();
	}
}
