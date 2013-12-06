package cleaner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;

public class SaveNLoad
{
	public static void main(String[] args)
	{
		File folder = new File(".");
		
//		try
//		{
//			WindowsShortcut ws = new WindowsShortcut(new File("alex.lnk"));
//			System.out.println(ws.getRealFilename());
//		} catch (Exception e){}
		
//		for(String drive : getDrives())
//			System.out.println(drive);
		
		for(File file : getFiles(folder))
			System.out.println(file.getName());
		
//		System.out.println(getNumberOfFilesInFolder(folder));
		
//		for(File file : getFilesRecur(folder))
//			System.out.println(file.getName());
	}
	
	public static void arrayToFile(String[] arr, String filePath)
	{
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(filePath));
			
			for(int i = 0; i < arr.length; i++)
			{
				writer.write(arr[i]);
				writer.newLine();
			}
			
			writer.flush();
			writer.close();

		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public static String[] fileToArray(String filePath)
	{
		filePath = filePath.replaceAll("\\\\", "/");
		try
		{
			List<String> lines;
			lines = Files.readAllLines(Paths.get(URI.create("file:///"+filePath.replaceAll(" ", "%20"))), Charset.forName("UTF-8"));
			String[] arr = lines.toArray(new String[lines.size()]);
			
			return arr;
			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return new String[]{};
	}
	
	public static String getDesktop()
	{
		return (System.getProperty("user.home") + "/Desktop").replaceAll("\\\\", "/");
	}
	
	public static void printFileContents(String path)
	{
		String[] stringArray = SaveNLoad.fileToArray(path);
		for (String str : stringArray)
			System.out.println(str);
	}
	
	public static int getNumberOfFilesInFolder(String folderPath)
	{
		return getNumberOfFilesInFolder(new File(folderPath));
	}
	
	public static int getNumberOfFilesInFolder(File folderPath)
	{
		return folderPath.listFiles().length;
	}
	
	public static void printFiles(File folder)
	{
		for(File f : getFiles(folder))
			System.out.println(f.getName());
	}
	
	public static ArrayList<File> getFiles(File folder)
	{
		ArrayList<File> fileList = new ArrayList<File>();
		
		for (File fileEntry : folder.listFiles())
		{
			if(fileEntry.isDirectory())
			{
				//do nothing
			}
			else
			{
//				System.out.println(fileEntry.getName());
				fileList.add(fileEntry);
			}
		}
		
		return fileList;
	}
	
	public static ArrayList<File> getFilesRecur(File folder)
	{
		ArrayList<File> fileList = new ArrayList<File>();
		
		for (File fileEntry : folder.listFiles())
		{
			if (fileEntry.isDirectory())
			{
//				System.out.println("~"+fileEntry.getName());
				for(File f : getFilesRecur(fileEntry))
					fileList.add(f);
			}
			else
			{
//				System.out.println(fileEntry.getName());
				fileList.add(fileEntry);
			}
		}
		
		return fileList;
	}
	
	public static ArrayList<String> getDrives()
	{
		ArrayList<String> output = new ArrayList<String>();
		File[] rootDrive = File.listRoots();
		for(File sysDrive : rootDrive)
			output.add(sysDrive.toString());
		return output;
	}
}
