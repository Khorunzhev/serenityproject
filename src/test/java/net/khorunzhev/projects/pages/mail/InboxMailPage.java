package net.khorunzhev.projects.pages.mail;

import net.thucydides.core.annotations.At;
import org.openqa.selenium.WebDriver;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

/**
 * Created by khorunzhev on 22.06.2018.
 */
@At("https://e.mail.ru/messages/inbox/*")
public class InboxMailPage extends MainMailPage {

    public InboxMailPage(WebDriver driver){
        super(driver);
    }

    public void checkThatInboxMailPageOpen() {
        getPages().currentPageAt(this.getClass());
        checkMailActionsList(new String[]{"Удалить", "Спам", "Переместить", "Ещё"});
    }

}
