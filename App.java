package testAutomation.testing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
       WebDriver wd = testAutomation.testing.SetupWebDriverClass.getDriver("Firefox");
       wd.get("https://www.google.com/");
    }
}
