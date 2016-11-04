package stepDefinition;

import static org.junit.Assert.assertFalse;
import cucumber.api.java.en.Then;
import helper.DriverHelper;
import pages.FacebookPage;
import pages.KlockiaPage;

public class ElemCheckJsOffSteps {

    DriverHelper driver = new DriverHelper();
    KlockiaPage klockiaPage = new KlockiaPage();
    FacebookPage facebookPage = new FacebookPage();

    @Then("^Facebook, Twitter logo is missing$")
    public void facebook_Twitter_logo_is_missing() throws Throwable {
        assertFalse(driver.isElementPresent(klockiaPage.facebookLogo));
    }

}
