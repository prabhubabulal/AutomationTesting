package testAutomation.testing;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import testAutomation.testing.TimeStamp;

public class SetupWebDriverClass {
	public static WebDriver driver;
	public static String pathToTestDownloadDirectory;
	
	public static String setPathToTestDownloadDirectory()
	{
		pathToTestDownloadDirectory = System.getProperty("basePathToTestDownloadDirectory")+TimeStamp.getFormattedDate("yyyyMMdd")+"\\"+TimeStamp.getFormattedTime()+"\\";
		return pathToTestDownloadDirectory;
	}

	public static String getPathToTestDownloadDirectory()
	{	
		return pathToTestDownloadDirectory;
	}

	@SuppressWarnings("unchecked")
	public static WebDriver getDriver(String browser_type)
	{
		pathToTestDownloadDirectory = setPathToTestDownloadDirectory();
		createTestDownloadDir(pathToTestDownloadDirectory);
		if( browser_type.equalsIgnoreCase("firefox"))
		{
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("browser.download.lastDir", pathToTestDownloadDirectory);
			profile.setPreference("browser.download.folderList", 2);
			profile.setPreference("browser.download.hide_plugins_without_extensions", true);
			profile.setPreference("browser.download.useDownloadDir", true);
			profile.setPreference("browser.download.defaultFolder", pathToTestDownloadDirectory);
			profile.setPreference("browser.download.dir", pathToTestDownloadDirectory);
			profile.setPreference("browser.download.downloadDir", pathToTestDownloadDirectory);
			profile.setPreference("dom.max_chrome_script_run_time", 0);
			profile.setPreference("dom.max_script_run_time", 0);
			profile.setPreference("browser.cache.disk.enable", false);
			profile.setPreference("browser.cache.memory.enable", false);
			profile.setPreference("browser.cache.offline.enable", false);
			profile.setPreference("network.http.use-cache", false);
			profile.setPreference("browser.helperApps.deleteTempFileOnExit", false);
			profile.setPreference("browser.helperApps.neverAsk.saveToDisk","application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/download,text/html,text/webviewhtml,application/vnd.ms-excel.sheet.macroEnabled.12,application/octet-stream,application/pdf,text/plain,application/xls,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/ms-excel");
			profile.setPreference("browser.helperApps.neverAsk.openFile","application/vnd.openxmlformats-officedocument.wordprocessingml.document,application/download,text/html,text/webviewhtml,application/vnd.ms-excel.sheet.macroEnabled.12,application/octet-stream,application/pdf,text/plain,application/xls,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet,application/ms-excel");
			profile.setPreference("pdfjs.disabled", true);
			profile.setPreference("plugin.scan.plid.all", false);
			profile.setPreference("plugin.scan.Acrobat", "99.0");
			profile.setPreference("browser.download.panel.shown", false);
			profile.setPreference("browser.download.manager.closeWhenDone", true);
			profile.setPreference("browser.download.manager.focusWhenStarting", false);
			profile.setPreference("browser.download.animateNotifications", false);
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
			dc.setCapability(FirefoxDriver.PROFILE, profile);
			driver = new FirefoxDriver(dc);
			driver.manage().deleteAllCookies();
		}

		else if( browser_type.equalsIgnoreCase("chrome"))
		{
			pathToTestDownloadDirectory = System.getProperty("basePathToTestDownloadDirectory")+TimeStamp.getFormattedDate("yyyyMMdd")+"\\"+TimeStamp.getFormattedTime()+"\\";
			createTestDownloadDir(pathToTestDownloadDirectory);

			System.out.println( browser_type +"  browser selected ");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\WebDrivers\\chromedriver.exe");

			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);

			HashMap<String, Object> plugin = new HashMap<String, Object>();
			plugin.put("enabled", false);
			plugin.put("name", "Chrome PDF Viewer");

			Map<String, Object> preferences = new HashMap<String, Object>();
			preferences.put("profile.default_content_settings.popups", 0);
			preferences.put("profile.default_content_settings.automaticDownloads", 0);
			preferences.put("profile.default_content_settings.automaticDownloads", "allow");
			preferences.put("chrome.contentSettings.automaticDownloads", "allow");
			preferences.put("download.default_directory", pathToTestDownloadDirectory);
			preferences.put("download.prompt_for_download", false);
			preferences.put("plugins.plugins_list", Arrays.asList(plugin));

			
		}
		else if(browser_type.equalsIgnoreCase("iexplore"))
		{
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\WebDrivers\\IEDriverServer.exe");
//			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
//			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//			ieCapabilities.setCapability("ignoreZoomSetting", true);
//			ieCapabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.DISMISS);
//			ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
//			driver = new InternetExplorerDriver(ieCapabilities);
//			driver.manage().window().maximize();
		}

		System.out.println("SetupWebDriverClass.getDriver : "+ browser_type);

		return driver;
	}


	public static void createTestDownloadDir(String pathToTestDownloadDirectory)
	{
		String pathToDatewiseDir = System.getProperty("basePathToTestDownloadDirectory")+TimeStamp.getFormattedDate("yyyyMMdd");

		try
		{
			File datewiseDir = new File(pathToDatewiseDir);

			if(!datewiseDir.exists())
			{
				datewiseDir.mkdir();
			}

			File testDownloadDir = new File(pathToTestDownloadDirectory);

			if(!testDownloadDir.exists())
			{
				testDownloadDir.mkdir();
			}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void logoutFromApplication() throws Exception
	{
		try{	
			driver.findElement(By.id("logoutForm:logout_link")).click();		
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutConfirmDialogMessage")));
			driver.findElement(By.id("logoutConfirmDialogDialog_form:buttonYes")).click();
			
		}catch(Exception e){}
	}

	public static void tearDown(boolean isException) throws Exception{

		try
		{
			if(!(driver==null))
			{
				System.out.println("In tearDown() !!!");
				if(!isException)
				{
					logoutFromApplication();
				}

				pathToTestDownloadDirectory = SetupWebDriverClass.getPathToTestDownloadDirectory();
				String path = pathToTestDownloadDirectory;
				File dirToBeDeleted = new File(path);

						}
		}

		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}