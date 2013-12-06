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
	
	public String toString()
	{
		return matchedTerm+" - "+file.getAbsolutePath();
	}
}
