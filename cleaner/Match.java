package cleaner;

import java.io.File;

import menusystem2.*;

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
		String str = StringParser.parseShortcut(actualFile.getAbsolutePath());
		
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
