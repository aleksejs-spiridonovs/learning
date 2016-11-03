package helper;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.Alert;

public class DriverHelper implements WebDriver {

	public static WebDriver driver;
	private final static WebDriverWait wait;
    public static final String browser = System.getProperty("browser");
    public static final String js = System.getProperty("js");
    
	public DriverHelper() {

	}

	static {
		if (browser.equalsIgnoreCase("firefox")) {
			if(js.equalsIgnoreCase("on")){
				driver = new FirefoxDriver();
			}else if(js.equalsIgnoreCase("off")){
				FirefoxProfile profile = new FirefoxProfile();
		    	profile.setPreference("javascript.enabled", false);
		    	driver = new FirefoxDriver(profile);
			}else{
				throw new RuntimeException("Please specify propery js on/off");
			}
	    }
		else if (browser.equalsIgnoreCase("chrome")) {
	        System.setProperty("webdriver.chrome.driver", "C:\\Users\\arturs.kucinskis\\Downloads\\chromedriver_win32(1)\\chromedriver.exe");
	        if(js.equalsIgnoreCase("on")){
	        	driver = new ChromeDriver();
	        }else if(js.equalsIgnoreCase("off")){
	        	driver = new ChromeDriver();
		        driver.navigate().to("chrome://settings-frame/content");
	            driver.findElement(By.xpath("//input[@type='radio' and @name='javascript' and @value='block']")).click();
	            driver.findElement(By.id("content-settings-overlay-confirm")).click();
	        }else{
	        	throw new RuntimeException("Please specify propery js on/off");
	        }
	    }
		else if (browser.equalsIgnoreCase("phantomJS")) {     
	        System.setProperty("phantomjs.binary.path","C:\\Users\\arturs.kucinskis\\Downloads\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
            driver = new PhantomJSDriver();
	    }  
	    else {
	        throw new RuntimeException("I don't think I should have made it here...");
	    }
		
		driver.manage().window().maximize();
		
		wait = (WebDriverWait) new WebDriverWait(driver, 60).ignoring(StaleElementReferenceException.class);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				driver.quit();
			}
		}, "Shutdown-thread"));
	}

	public void waitForElementPresent(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public void waitForElementPresent(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForTextPresentInElement(WebElement element, String text) {
		wait.until(ExpectedConditions.textToBePresentInElement(element, text));
	}

	public void waitForInvisibilityOfElement(By by) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public void waitForVisibilityOfElementFromList(List<WebElement> list) {
		wait.until(ExpectedConditions.visibilityOfAllElements(list));
	}

	public void waitForInvisibilityOfElement(final WebElement element) {
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (!isElementPresent(element)) {
					return true;
				} else {
					return false;
				}
			}
		});
	}

	public void waitForExpectedSizeInList(final int expectedSize, final List<WebElement> list) {
		try {
			wait.until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					if (list.size() == expectedSize) {
						return true;
					} else {
						return false;
					}
				}
			});
		} catch (TimeoutException e) {
			Assert.fail("Expected size:<" + expectedSize + "> but was:<" + list.size() + ">");
		}
	}

	/**
	 *
	 * @param by
	 *            - locator of element you wish to search for
	 * @return true if element is present, displayed, one of many with the same
	 *         by and enabled false if not
	 *
	 */
	public boolean isElementPresent(By by) {
		if (driver.findElements(by).size() > 0) {
			driver.findElement(by);
			if (driver.findElement(by).isDisplayed()) {
				if (driver.findElement(by).isEnabled()) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isElementPresent(WebElement element) {
		try {
			element.getTagName();
			return true;
		} catch (NoSuchElementException nsee) {
			return false;
		}
	}

	/**
	 *
	 * Temporary properties will allow data to be passed between steps or even
	 * scenarios
	 *
	 * @param key
	 *            - Identifier for the value being added
	 * @param value
	 *            - value you wish to store
	 *
	 *
	 */

	public void close() {
		driver.close();
	}

	public WebElement findElement(By arg0) {
		return driver.findElement(arg0);
	}

	public List<WebElement> findElements(By arg0) {
		return driver.findElements(arg0);
	}

	public void get(String arg0) {
		driver.get(arg0);
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	public Options manage() {
		return driver.manage();
	}

	public Navigation navigate() {
		return driver.navigate();
	}

	public void quit() {
		driver.quit();
	}

	public TargetLocator switchTo() {
		return driver.switchTo();
	}

	public void scrollUp() {
		((JavascriptExecutor) driver).executeScript("scroll(250,0);");
	}

	public void scrollDown() {
		((JavascriptExecutor) driver).executeScript("scroll(0,250);");
	}
	
	public void scrollDown(int pixels) {
		((JavascriptExecutor) driver).executeScript("scroll(0,"+pixels+");");
	}

	public void scrollTo(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
	}

	public void clearLocalStorage() {
		((JavascriptExecutor) driver).executeScript("localStorage.clear()");
	}
	
	public void clickElement(WebElement element){
		
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
//		((JavascriptExecutor)driver).executeScript("document.getElementsById().click();");
	}
	
	public void clickElementNoJs(By element){
		driver.findElement(element).click();
	}
	
	public void hoverOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element);
		action.build().perform();
	}

	public byte[] captureScreenshot() throws IOException {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	public String getSelectedDropdownOption(WebElement dropdown) {
		// Select selectedDropdown = new Select(dropdown);
		return new Select(dropdown).getFirstSelectedOption().getText();
	}

	public void goToPage(String url) {
		driver.get(url);
	}

	public void typeText(String value, String text) {
		driver.findElement(By.id(value)).sendKeys(text);
	}
	
	public String getRgbToHexColor(By element, String colorAttribute){ 
		 String color = driver.findElement(element).getCssValue(colorAttribute).trim();  		  
		 String color1[];  
		 color1 = color.replace("rgba(", "").split(",");       
		 String hex = String.format("#%02x%02x%02x", Integer.parseInt(color1[0].trim()), Integer.parseInt(color1[1].trim()), Integer.parseInt(color1[2].trim()));    
		 return hex;
	}
	
	public String getCssAttributeValue(By element, String attribute) {
		String value = driver.findElement(element).getCssValue(attribute);
		return value;
	}
	
	public void acceptAlert() {
		((JavascriptExecutor) driver).executeScript("window.alert = function(){}");
		((JavascriptExecutor) driver).executeScript("window.confirm = function(){return true;}");
	}
	
	public void dismissAlert() {
		((JavascriptExecutor) driver).executeScript("window.alert = function(){}");
		((JavascriptExecutor) driver).executeScript("window.confirm = function(){return false;}");
	}

}
