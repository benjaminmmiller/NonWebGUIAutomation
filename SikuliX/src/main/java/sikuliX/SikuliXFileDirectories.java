package sikuliX;

import utils.FileUtils;

public class SikuliXFileDirectories {
	private final static String imagesFolderPath = FileUtils.getProjectFilePath() +"\\src\\main\\resources\\images";
	private final static String outputImagePath = FileUtils.getProjectFilePath() + "\\test-output\\screenshots";
	private final static String outputDynamicImagePath = FileUtils.getProjectFilePath() + "\\src\\main\\resources\\dynamic-images";
	private final static String outputTextPath = FileUtils.getProjectFilePath() + "\\test-output\\textFiles";
	
	
	
	public static String getImagesFolderPath() {
		FileUtils.createDirectory(imagesFolderPath, false);
		return imagesFolderPath;
	}
	public static String getOutputImagePath() {
		FileUtils.createDirectory(outputImagePath, false);
		return outputImagePath;
	}
	public static String getOutputDynamicImagePath() {
		FileUtils.createDirectory(outputDynamicImagePath, false);
		return outputDynamicImagePath;
	}
	public static String getOutputTextPath() {
		FileUtils.createDirectory(outputTextPath, false);
		return outputTextPath;
	}
}
