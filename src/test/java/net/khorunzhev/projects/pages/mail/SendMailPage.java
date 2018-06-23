package net.khorunzhev.projects.pages.mail;

import org.openqa.selenium.WebDriver;

/**
 * Created by khorunzhev on 22.06.2018.
 */
public class SendMailPage extends MainMailPage {

    public SendMailPage(WebDriver driver){
        super(driver);
    }

    public void checkActionsOnSentPage(String[] actions){
        checkMailActionsList(actions);
    }

}
