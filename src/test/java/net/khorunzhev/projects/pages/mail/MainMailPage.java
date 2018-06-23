package net.khorunzhev.projects.pages.mail;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by khorunzhev on 22.06.2018.
 */
public class MainMailPage extends PageObject {

    @FindBy(xpath="//a[@data-name='compose']/span")
    private WebElementFacade composeMailButton;

    @FindBy(id="PH_user-email")
    private WebElementFacade userEmailLabel;

    @FindBy(xpath="//a[@href='/messages/sent/']")
    private WebElementFacade outboxNavMenu;

    public MainMailPage(WebDriver driver){
        super(driver);
    }

    public void clickComposeMailButton(){
        composeMailButton.click();
    }

    public boolean isUserAutorized(String login) {
       return userEmailLabel.waitUntilPresent().getText().equals(login + "@mail.ru");
    }

    public void goToOutBoxList() {
        outboxNavMenu.click();
    }


    public void checkMailActionsList(String[] actions){
        List<WebElement> listOfActions = getDriver().findElements(By.xpath("//div[@id='b-toolbar__right']//span"));
        for(String action: actions) {
            Assert.assertTrue(listOfActions.stream().anyMatch(t -> t.isDisplayed() && t.getText().equals(action)));
        }

    }

    public List<WebElement> getMailList() {
        return getDriver().findElements(By.xpath("//div[@class='b-datalist__item__subj']"));
    }

    public boolean checkFirstElementInMailList(String title) {
         return ((getMailList().get(0).isDisplayed()) && (getMailList().get(0).getText().equals(title)));
    }

}
