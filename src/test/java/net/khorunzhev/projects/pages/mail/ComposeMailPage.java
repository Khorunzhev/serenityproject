package net.khorunzhev.projects.pages.mail;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by khorunzhev on 22.06.2018.
 */
public class ComposeMailPage extends MainMailPage {

    @FindBy(xpath="//textarea[@data-original-name='To']")
    private WebElementFacade toWhomInput;

    @FindBy(xpath="//input[@name='Subject']")
    private WebElementFacade titleInput;

    @FindBy(xpath="//input[@name='Filedata']")
    private WebElementFacade fileInput;

    @FindBy(xpath="(//div[@data-name='send'])[1]")
    private WebElementFacade mailSendButton;


    public ComposeMailPage(WebDriver driver){
        super(driver);
    }

    public void checkActionsOnComposePage(String[] actions){
        checkMailActionsList(actions);
    }

    public void setToWhomInput(String toWhomText){
        toWhomInput.type(toWhomText);
    }

    public void setTitleInput(String titleText){
        titleInput.type(titleText);
    }

    public void setMailTextInput(String mailText) {
        List<WebElement> frames = getDriver().findElements(By.xpath("//iframe[contains(@id, 'toolkit')]"));
        if (frames.size() == 1) {
            getDriver().switchTo().frame(frames.get(0));
            WebElement textBody = getDriver().switchTo().activeElement();
            textBody.sendKeys(mailText);
            getDriver().switchTo().defaultContent();
        }
        else {
            throw new ElementNotSelectableException("Невозможно переключиться в поле ввода письма, т.к. frame = " + frames.size());
        }
    }

    public void uploadFile(String filePath) {
        ((JavascriptExecutor)getDriver()).executeScript("document.getElementsByName('Filedata')[0].setAttribute('class','')");
        upload(filePath).fromClasspath().to(fileInput);
    }

    public void clickMailSendButton(){
        mailSendButton.click();
    }

}
