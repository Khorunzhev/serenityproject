package net.khorunzhev.projects.pages.mail;

import net.serenitybdd.core.annotations.findby.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by khorunzhev on 22.06.2018.
 */
public class SendStatusPage extends MainMailPage {

    public SendStatusPage(WebDriver driver){
        super(driver);
    }

    public boolean iSMailSent() {
        WebElement statusTitle = getDriver().findElement(By.xpath("//div[@class='message-sent__title']"));
        return statusTitle.getText().equals("Ваше письмо отправлено. Перейти во Входящие");
    }

}
