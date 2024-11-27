package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {

	public static WebDriver driver;
	
	public Logger logger;
	
	public Properties p;
	
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	
	public void setup(String os, String br) throws IOException {
		
		//loading config.properties
		FileReader file =new FileReader("./src//test//resources//config.properties");	
		p=new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());
		
		// remote env
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities cp= new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				cp.setPlatform(Platform.WIN11);
			}
			else if (os.equalsIgnoreCase("mac")) {
				cp.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no matching os");
				return;
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": cp.setBrowserName("chrome"); break;
			case "edge" : cp.setBrowserName("MicrosoftEdge"); break;
			default : System.out.println("no matching Browser"); return;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cp);
		}
		
		//local env
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		
			switch(br.toLowerCase()) {
			case "chrome" :driver = new ChromeDriver(); break;
			case "edge" :driver = new EdgeDriver(); break;
			case "firefox" :driver = new FirefoxDriver(); break;
			default:System.out.println("invalid browser name.."); return;
			
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//driver.get("https://tutorialsninja.com/demo/");    // or
		driver.get(p.getProperty("appURl"));		// reading url from properties file
		
		driver.manage().window().maximize();
		
	}
	
	
	@AfterClass(groups={"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	
	
	@SuppressWarnings("deprecation")
	public String randomString() 
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		
	}
	@SuppressWarnings("deprecation")
	public String randomNumber() 
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
		
	}
	
	@SuppressWarnings("deprecation")
	public String randomAlphaNumber() 
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		return (generatedstring +"@" + generatednumber);
		
	}


	public String captureScreen(String tname) throws IOException {

		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
		File sourceFile =takesScreenshot.getScreenshotAs (OutputType.FILE);

		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname +"_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);

		sourceFile.renameTo(targetFile);

		return targetFilePath;



	}

	
}
