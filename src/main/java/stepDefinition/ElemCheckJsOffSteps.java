package stepDefinition;

import static org.junit.Assert.assertFalse;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helper.DriverHelper;
import pages.FacebookPage;
import pages.KlockiaPage;

public class ElemCheckJsOffSteps {

	DriverHelper driver = new DriverHelper();
	KlockiaPage klockiaPage = new KlockiaPage();
	FacebookPage facebookPage = new FacebookPage();

	@When("^I click on “Calvin Klein City Chrono” watch$")
	public void i_click_on_Calvin_Klein_City_Chrono_watch() throws Throwable {
		driver.waitForElementPresent(klockiaPage.watchCalvinKleinNoJs);
		driver.clickElementNoJs(klockiaPage.watchCalvinKleinNoJs);
	}

	@Then("^Facebook, Twitter logo is missing$")
	public void facebook_Twitter_logo_is_missing() throws Throwable {
		assertFalse(driver.isElementPresent(klockiaPage.facebookLogo));
	}

}