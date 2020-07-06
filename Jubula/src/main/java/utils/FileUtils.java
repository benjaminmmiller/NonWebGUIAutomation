package utils;

import java.io.File;
import java.io.IOException;

public class FileUtils {
	
	public static String getProjectFilePath() {
		return System.getProperty("user.dir");
	}
	
	public static File createDirectory(String path, boolean emptyDirectory) {
		File newDirectory = new File(path);
		if(newDirectory.exists()==false) {
			newDirectory.mkdirs();
		}
		if(emptyDirectory==true) {
			try {
				org.apache.commons.io.FileUtils.cleanDirectory(newDirectory);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return newDirectory;
	}
	
}
