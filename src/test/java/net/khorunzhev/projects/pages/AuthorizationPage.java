package net.khorunzhev.projects.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by khorunzhev on 22.06.2018.
 */

@DefaultUrl("https://mail.ru")
public class AuthorizationPage extends PageObject {

    @FindBy(xpath="//label[@id='mailbox:submit']")
    private WebElementFacade autorizationSubmitButton;

    @FindBy(xpath="//input[@id='mailbox:login']")
    private WebElementFacade loginInput;

    @FindBy(xpath="//input[@id='mailbox:password']")
    private WebElementFacade passwordInput;

    public AuthorizationPage(WebDriver driver){
        super(driver);
    }

    public void checkLoginButtonIsVisible() {
        Assert.assertTrue(autorizationSubmitButton.isDisplayed());
    }

    public void loginToMail(String login, String password) {
        //getDriver().navigate().refresh();
        loginInput.type(login);
        passwordInput.type(password);
        autorizationSubmitButton.click();
    }

}
