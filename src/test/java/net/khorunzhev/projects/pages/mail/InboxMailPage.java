package net.khorunzhev.projects.pages.mail;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

/**
 * Created by khorunzhev on 22.06.2018.
 */

public class InboxMailPage extends MainMailPage {

    public InboxMailPage(WebDriver driver){
        super(driver);
    }

    public void checkActionsOnInboxPage(String[] actions){
        checkMailActionsList(actions);
    }

}
