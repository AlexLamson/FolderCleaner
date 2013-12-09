package cleaner;

import java.io.File;

public class Match
{
	public File file;						//matched file
	public String matchedTerm;				//black/white-listed term
	public boolean isBlacklisted = false;	//if false then whitelisted
	
	public Match(File file, String matchedTerm, boolean isBlacklisted)
	{
		this.file = file;
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isBlacklisted;
	}
	
	public Match(String fileStr, String matchedTerm, boolean isBlacklisted)
	{
		this.file = new File(fileStr);
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isBlacklisted;
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
		return matchedTerm+spaces+"- "+file.getAbsolutePath();
	}
	
	public String toString()
	{
		return matchedTerm+"\t- "+file.getAbsolutePath();
	}
}
