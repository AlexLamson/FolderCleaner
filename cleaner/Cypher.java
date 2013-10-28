package cleaner;

import java.util.ArrayList;

public class Cypher
{
	public static int cypherShift = 5;
	
	public static void updateShift(char fake, char real)
	{
		cypherShift = fake-real;
	}
	
	public static ArrayList<String> unobfuscate(ArrayList<String> strings)
	{
		ArrayList<String> newStrings = new ArrayList<String>();
		for(int i = 0; i < strings.size(); i++)
			newStrings.add(unobfuscate(strings.get(i)));
		return newStrings;
	}
	
	public static String unobfuscate(String str)
	{
		String newString = "";
		for(int i = 0; i < str.length(); i++)
			newString += unobfuscate(str.charAt(i));
		return newString;
	}
	
	public static char unobfuscate(char ch)
	{
		return (char)( ch - cypherShift );
	}
	
	public static ArrayList<String> obfuscate(ArrayList<String> strings)
	{
		ArrayList<String> newStrings = new ArrayList<String>();
		for(int i = 0; i < strings.size(); i++)
			newStrings.add(obfuscate(strings.get(i)));
		return newStrings;
	}
	
	public static String obfuscate(String str)
	{
		String newString = "";
		for(int i = 0; i < str.length(); i++)
			newString += obfuscate(str.charAt(i));
		return newString;
	}
	
	public static char obfuscate(char ch)
	{
		return (char)( ch + cypherShift );
	}
	
	//change all non-letters to spaces and remove any repeated spaces to one space
	public static String cleanString(String inString)
	{
		String outString = "";
		
		for(int i = 0; i < inString.length(); i++)
		{
			char c = inString.charAt(i);
			if( !((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) )
				c = ' ';
			outString = outString + c;
			if(outString.endsWith("  "))
				outString = outString.substring(0, outString.length()-1);
		}
		
		outString = outString.trim();
		return outString;
	}
}
