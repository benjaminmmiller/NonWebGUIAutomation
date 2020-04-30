package sikuliX;

import utils.TestingFrameworkFileUtils;

public class SikuliXFileDirectories {
	private final static String imagesFolderPath = TestingFrameworkFileUtils.getProjectFilePath() +"\\src\\main\\resources\\images";
	private final static String outputImagePath = TestingFrameworkFileUtils.getProjectFilePath() + "\\test-output\\screenshots";
	private final static String outputDynamicImagePath = TestingFrameworkFileUtils.getProjectFilePath() + "\\src\\main\\resources\\dynamic-images";
	private final static String outputTextPath = TestingFrameworkFileUtils.getProjectFilePath() + "\\test-output\\textFiles";
	
	
	
	public static String getImagesFolderPath() {
		TestingFrameworkFileUtils.createDirectory(imagesFolderPath, false);
		return imagesFolderPath;
	}
	public static String getOutputImagePath() {
		TestingFrameworkFileUtils.createDirectory(outputImagePath, false);
		return outputImagePath;
	}
	public static String getOutputDynamicImagePath() {
		TestingFrameworkFileUtils.createDirectory(outputDynamicImagePath, false);
		return outputDynamicImagePath;
	}
	public static String getOutputTextPath() {
		TestingFrameworkFileUtils.createDirectory(outputTextPath, false);
		return outputTextPath;
	}
	
}
