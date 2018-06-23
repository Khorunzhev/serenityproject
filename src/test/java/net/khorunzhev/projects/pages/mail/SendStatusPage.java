package net.khorunzhev.projects.pages.mail;

import net.serenitybdd.core.annotations.findby.By;
import net.thucydides.core.annotations.At;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

/**
 * Created by khorunzhev on 22.06.2018.
 */
@At("https://e.mail.ru/sendmsgok/*")
public class SendStatusPage extends MainMailPage {
    public SendStatusPage(WebDriver driver){
        super(driver);
    }

    public void checkThatSendStatusMailPageOpen() {
        getPages().currentPageAt(this.getClass());
    }

    public boolean iSMailSent() {
        WebElement statusTitle = getDriver().findElement(By.xpath("//div[@class='message-sent__title']"));
        return statusTitle.getText().equals("Ваше письмо отправлено. Перейти во Входящие");
    }

}
