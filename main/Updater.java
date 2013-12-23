package main;

import java.util.ArrayList;

import cleaner.MatchList;

//very static class to hold the current progress of the scan
public class Updater
{
	public static ArrayList<MatchList> allLists = new ArrayList<MatchList>();
	
	public static int totalFiles = 0;
	public static int scannedFiles = 0;
}
