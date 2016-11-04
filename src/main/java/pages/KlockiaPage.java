package pages;

import org.openqa.selenium.By;

public class KlockiaPage {

    public By klockor = By.xpath("//a[contains(text(),'KLOCKOR')]");
    public By watchCalvinKlein = By.cssSelector("#p-347");
    public By watchCalvinKleinNoJs = By.xpath("//div[@id='p-347']/div[1]/div/a");
    public By calvinKleinBuyButtonNoJsColor = By.xpath("//input[@id='347']");
    public By buyButton = By.cssSelector("#p-347>div.hover>div.list-buttons>div>div>form>a");
    public By readMoreButton = By.cssSelector("#p-347>div.hover>div.list-buttons>div>div");
    public By watchMarkJacobs = By.cssSelector("#p-3779");
    public By watchMarkJacobsHovered = By.xpath("//div[@id='p-3779']/div/div/a");
    public By facebookLogo = By.cssSelector("div.at-share-btn-elements>:first-child");
}
