package main;

import java.util.ArrayList;

import cleaner.Lists;
import cleaner.Match;
import cleaner.MatchList;

//very static class to hold the current progress of the scan
public class Updater
{
	public static ArrayList<MatchList> allLists = new ArrayList<MatchList>();
	
	public static ArrayList<Match> cacheMatches = new ArrayList<Match>();
	public static ArrayList<Match> historyMatches = new ArrayList<Match>();
	
	public static int totalFiles = 0;
	public static int scannedFiles = 0;
	
	public static void setTotalFiles(int total)
	{
		totalFiles = total;
		MakeMenu.loadingbar.setMax(total);
	}
	
	public static void incrementScannedFiles()
	{
		totalFiles++;
		MakeMenu.loadingbar.setCurrent(totalFiles);
	}
	
	public static void clean()
	{
		System.out.println("Cleaning...");
//		Lists.deleteMatchedFiles = true;
		runScan();
	}
	
	public static void scan()
	{
		System.out.println("Scanning...");
		runScan();
	}
	
	private static void runScan()
	{
		Lists.loadLists();
		historyMatches = Lists.getMatches(Lists.historyFolders, true);
		cacheMatches = Lists.getMatches(Lists.cacheFolders, true);
	}
	
	public static void finishScan()
	{
		
	}
	
	public static void removeLastNMinutes(int n)
	{
		
	}
}
