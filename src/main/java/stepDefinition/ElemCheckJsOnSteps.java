package stepDefinition;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.Alert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.DriverHelper;
import pages.FacebookPage;
import pages.KlockiaPage;

public class ElemCheckJsOnSteps {

	DriverHelper driver = new DriverHelper();
	KlockiaPage klockiaPage = new KlockiaPage();
	FacebookPage facebookPage = new FacebookPage();
	String homepage;
	String currentWindow;
	String facebookWindow;
	String browser = System.getProperty("browser");
	String js = System.getProperty("js");

	@Given("^I go to http://www\\.klockia\\.se/$")
	public void i_go_to_http_www_klockia_se() throws Throwable {
		driver.goToPage("http://www.klockia.se/");
	}

	@When("^I click on “KLOCKOR” button$")
	public void i_click_on_KLOCKOR_button() throws Throwable {
		if(js.equalsIgnoreCase("on")){
			driver.waitForElementPresent(klockiaPage.klockor);
			driver.clickElement(driver.findElement(klockiaPage.klockor));
		}else if(js.equalsIgnoreCase("off")){
			driver.clickElementNoJs(klockiaPage.klockor);
		}else{
			throw new RuntimeException("Please specify propery js on/off");
		}
	}

	@When("^mousover on “Calvin Klein City Chrono” watch$")
	public void mousover_on_Calvin_Klein_City_Chrono_watch() throws Throwable {
		driver.scrollDown(450);
		driver.waitForElementPresent(klockiaPage.watchCalvinKlein);
		driver.hoverOverElement(driver.findElement(klockiaPage.watchCalvinKlein));
	}

	@Then("^I validate that “KÖP” background color is equal to \"([^\"]*)\"$")
	public void i_validate_that_KÖP_background_color_is_equal_to_f_b(String color) throws Throwable {
		if(js.equalsIgnoreCase("on")){
			driver.waitForElementPresent(klockiaPage.buyButton);
			String backgroundColor = driver.getRgbToHexColor(klockiaPage.buyButton, "background-color");
			assertTrue(backgroundColor.equals(color));
		}else if(js.equalsIgnoreCase("off")){
			String backgroundColor = driver.getRgbToHexColor(klockiaPage.calvinKleinBuyButtonNoJsColor, "background-color");
			assertTrue(backgroundColor.equals(color));
		}else{
			throw new RuntimeException("Please specify propery js on/off");
		}
		
	}

	@Then("^“LÄS MER” background color is equal to \"([^\"]*)\"$")
	public void läs_MER_background_color_is_equal_to(String color) throws Throwable {
		driver.waitForElementPresent(klockiaPage.readMoreButton);
		driver.hoverOverElement(driver.findElement(klockiaPage.readMoreButton));
		String backgroundColor = driver.getRgbToHexColor(klockiaPage.readMoreButton, "color");
		assertTrue(backgroundColor.equals(color));
	}

	@Then("^padding of elements is equal to \"([^\"]*)\"$")
	public void padding_of_elements_is_equal_to_px(String value) throws Throwable {
		if(js.equalsIgnoreCase("on")){
			driver.waitForElementPresent(klockiaPage.buyButton);
			String padding = driver.getCssAttributeValue(klockiaPage.buyButton, "padding-top");
			assertTrue(padding.equals(value));
		}else if(js.equalsIgnoreCase("off")){
			String padding = driver.getCssAttributeValue(klockiaPage.calvinKleinBuyButtonNoJsColor, "padding-right");
		    assertTrue(padding.equals(value));
		}else{
			throw new RuntimeException("Please specify propery js on/off");
		}
		
	}

	@When("^I click on “Marc By Marc Jacobs Baker” watch$")
	public void i_click_on_Marc_By_Marc_Jacobs_Baker_watch() throws Throwable {
		driver.scrollDown(450);
		driver.waitForElementPresent(klockiaPage.watchMarkJacobs);
		driver.clickElement(driver.findElement(klockiaPage.watchMarkJacobsHovered));
	}

	@When("^on the new opened window click on Facebook logo$")
	public void on_the_new_opened_window_click_on_Facebook_logo() throws Throwable {
		homepage = driver.getWindowHandle();
		driver.scrollDown(500);
		driver.waitForElementPresent(klockiaPage.facebookLogo);
		driver.clickElement(driver.findElement(klockiaPage.facebookLogo));
	}
	
	@When("^I log in with my facebook account$")
	public void i_log_in_with_my_facebook_account() throws Throwable {
		Set<String> windows = driver.getWindowHandles();
		Iterator iterator = windows.iterator();

		while (iterator.hasNext()) {
			currentWindow = iterator.next().toString();
			if (!currentWindow.equals(homepage)) {
				driver.switchTo().window(currentWindow);
				facebookWindow = driver.getWindowHandle();
				driver.waitForElementPresent(facebookPage.Email);
				driver.findElement(facebookPage.Email).sendKeys("mytestingemail@inbox.lv");
				driver.findElement(facebookPage.Password).sendKeys("Test151");
				driver.clickElement(driver.findElement(facebookPage.LoginButton));				
			}
		}
	}

	@When("^close Facebook window$")
	public void close_Facebook_window() throws Throwable {
		driver.waitForElementPresent(facebookPage.Close);
		driver.clickElement(driver.findElement(facebookPage.Close));
	}

	@Then("^press on “Stay” and validate that Facebook page is visible$")
	public void press_on_Stay_and_validate_that_Facebook_page_is_visible() throws Throwable {
		if(browser.equalsIgnoreCase("phantomjs")){
		driver.dismissAlert();
		}else{
		Alert alert = driver.switchTo().alert();
		alert.dismiss();		
		}
		currentWindow = driver.getWindowHandle();
		assertTrue(currentWindow.equals(facebookWindow));
	}

	@Then("^press on “Leave” and validate that Facebook page is not visible$")
	public void press_on_Leave_and_validate_that_Facebook_page_is_not_visible() throws Throwable {
		driver.waitForElementPresent(facebookPage.Close);
		driver.clickElement(driver.findElement(facebookPage.Close));
		if(browser.equalsIgnoreCase("phantomjs")){
			driver.acceptAlert();
			}else{
			Alert alert = driver.switchTo().alert();
			alert.accept();			
			}
		driver.switchTo().window(homepage);
		currentWindow = driver.getWindowHandle();
		assertTrue(currentWindow.equals(homepage));
	}

}
