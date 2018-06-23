package net.khorunzhev.projects.pages.mail;

import static net.khorunzhev.projects.support.MailPagesStrings.*;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

/**
 * Created by khorunzhev on 22.06.2018.
 */
@At("https://e.mail.ru/compose/*")
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

    public void checkThatComposeMailPageOpen() {
        getPages().currentPageAt(this.getClass());
        checkMailActionsList(new String[]{sendButtonText, saveButtonText, cancleButtonText});
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
