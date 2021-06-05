package util.general;

import java.io.File;
import java.io.FileFilter;

public class FileTools 
{
	
	public static File getLastDownloadedFile() {
	    File choice = null;
	    try {
	        File fl = new File("C:/Users/" + System.getProperty("user.name")
	                + "/Downloads/");
	        File[] files = fl.listFiles(new FileFilter() {
	            public boolean accept(File file) {
	                return file.isFile();
	            }
	        });
	        Thread.sleep(60000);
	        long lastMod = Long.MIN_VALUE;

	        for (File file : files) {
	            if (file.lastModified() > lastMod) {
	                choice = file;
	                lastMod = file.lastModified();
	            }
	        }
	    } catch (Exception e) {
		       e.printStackTrace();
	    }
	    System.out.println("The last downloaded file is " + choice.getPath());
	    System.out.println("The last downloaded file is " + choice.getPath());
	    return choice;
	}


}
