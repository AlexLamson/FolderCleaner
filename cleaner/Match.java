package cleaner;

import java.io.File;

import menusystem2.*;

public class Match
{
	public File actualFile;		//actual file being checked
	public File linkedFile;		//file being linked to
	public String matchedTerm;				//black/white-listed term (if length == 1 then placeholder match)
	public boolean isBlacklisted = false;	//if false then whitelisted
	public File blacklistUsed;				//the black/whitelist used to make this match
	
	public Match(File file, File blacklist, String matchedTerm, boolean isBlacklisted)
	{
		this.actualFile = file;
		getLinkedFile();
		this.matchedTerm = matchedTerm;
		this.isBlacklisted = isBlacklisted;
		blacklistUsed = blacklist;
	}
	
	public void getLinkedFile()
	{
		String str = actualFile.getAbsolutePath();
		
		if(actualFile.exists())
			str = StringParser.parseShortcut(actualFile.getAbsolutePath());
		
		if(!str.equals(actualFile.getAbsolutePath()))
			System.out.println("actual = linked: "+linkedFile.getAbsolutePath());
		
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
		
		String absPath = linkedFile.getAbsolutePath();
		if(absPath.length() <= 7)
			return blacklistUsed.getName()+" \'"+matchedTerm+"\'";
		
//		blacklistUsed.getName();
//		linkedFile.getAbsolutePath();
		
//		String str = blacklistUsed.getName()+" \'"+matchedTerm+"\'"+spaces+"- "+linkedFile.getAbsolutePath();
		String str = "\'"+matchedTerm+"\'"+spaces+"- "+linkedFile.getAbsolutePath();
//		String str = linkedFile.getAbsolutePath();
//		String str = absPath.length()+"";
//		String str = absPath.substring(0, 8);
//		String str = absPath.substring(absPath.length()-8, absPath.length());
//		String str = absPath.substring(0, absPath.length());
		return str;
	}
	
	public String toString()
	{
		return matchedTerm+"\t- "+linkedFile.getAbsolutePath();
	}
	
	public Menu toMenu()
	{
		Menu menu = new Menu(10, 1, true);
		menu.setPadding(0, 0);
		menu.setColsRows(20, 1);
		
		BooleanButton shouldDeleteButton = new BooleanButton(0, 0, 1, 1, true, "O", "X");
		shouldDeleteButton.boolState = !isBlacklisted;
		shouldDeleteButton.useInvertedText = false;
		menu.addMenu(shouldDeleteButton);
		
		BooleanButton matchButton = new BooleanButton(1, 0, 4, 1, true, matchedTerm);
		matchButton.useSetFontSize = true;
		matchButton.fontSize = 12;
		matchButton.useInvertedText = false;
		matchButton.changeColorWhenClicked = false;
		matchButton.boolState = !isBlacklisted;
		menu.addMenu(matchButton);
		
		Button pathButton = new Button(5, 0, 15, 1, true, linkedFile.getAbsolutePath());
//		pathButton.setColor(new Color(0, 109, 193));
		pathButton.useSetFontSize = true;
		pathButton.fontSize = 18;
		pathButton.textAlignment = -1;
		pathButton.changeColorWhenClicked = false;
		pathButton.useInvertedText = false;
		menu.addMenu(pathButton);
		
		return menu;
	}
}
