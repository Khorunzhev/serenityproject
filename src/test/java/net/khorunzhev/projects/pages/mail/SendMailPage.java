package net.khorunzhev.projects.pages.mail;

import net.thucydides.core.annotations.At;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

/**
 * Created by khorunzhev on 22.06.2018.
 */
@At("https://e.mail.ru/messages/sent/")
public class SendMailPage extends MainMailPage {

    public SendMailPage(WebDriver driver){
        super(driver);
    }

    public void checkThatSendMailPageOpen() {
        getPages().currentPageAt(this.getClass());
        checkMailActionsList(new String[]{"Удалить", "Спам", "Переместить", "Ещё"});
    }

}
