package cleaner;

import java.io.File;

public class Match
{
	public File file;						//matched file
	public String matchedTerm;				//black/white-listed term
	public boolean isBlacklisted = false;	//if false then whitelisted
	
	public Match(File file, String matchedTerm, boolean isMatch)
	{
		isMatch = true;
		this.file = file;
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isMatch;
	}
	
	public Match(String fileStr, String matchedTerm, boolean isMatch)
	{
		isMatch = true;
		this.file = new File(fileStr);
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isMatch;
	}
	
	public boolean isNull()
	{
		return matchedTerm.length() == 0;
	}
	
	public String toString()
	{
		return matchedTerm+" - "+file.getAbsolutePath();
	}
}
