package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class RestUtils {
	private static String reportFolderLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash + "test-output"
			+ GlobalParam.slash + "Report";

	private static String screenshotFolderLocation = GlobalParam.CURRENT_PROJECT_PATH + GlobalParam.slash
			+ "Screenshots";

	public static void clearReportsFromReportsFolder() {
		File reportFile = new File(reportFolderLocation);
		if (reportFile.exists()) {
			File[] files = reportFile.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	public static void clearScreenshotFromScreenshotFolder() {
		File reportFile = new File(screenshotFolderLocation);
		if (reportFile.exists()) {
			File[] files = reportFile.listFiles();
			for (File file : files) {
				file.delete();
			}
		}
	}

	public static String captureScreenshot(WebDriver driver, String testCaseName) {
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String screenshotLocation = screenshotFolderLocation + GlobalParam.slash + testCaseName + ".png";		
		File destFile = new File(screenshotLocation);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return screenshotLocation;
	}
}
