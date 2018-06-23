package net.khorunzhev.projects.features;

import net.khorunzhev.projects.steps.serenity.MailNavigationUserSteps;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.time.LocalDateTime;

/**
 * Created by khorunzhev on 22.06.2018.
 */

@RunWith(SerenityParameterizedRunner.class)
@WithTags({
        @WithTag("sendMailStory")
})
@UseTestDataFrom(value="testdata/maildata.csv", separator=';')
public class SendMailStory {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @Steps
    public MailNavigationUserSteps mailNavigationSteps;

    private String login;
    private String password;

    private String toWhom;
    private String title;
    private String mailText;
    private  String fileLocation;

    public void setToWhom(String toWhom) {
        this.toWhom = toWhom;
    }

    public void setTitle(String title) {
        this.title = title + LocalDateTime.now();
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Before
    public void setupBrowser() {
        webdriver.manage().window().maximize();
    }

    @Test
    @WithTag("mailWithData")
    public void sendMailWithDataTest() {
        //Given
        mailNavigationSteps.userOpenAutorizationPage();

        //When
        mailNavigationSteps.userAuthorized(login, password);

        //Then
        mailNavigationSteps.mainMailPageOpen();

        //When
        mailNavigationSteps.userClickWriteMailButton();

        //Then
        mailNavigationSteps.composeMailPageOpen();

        //When
        mailNavigationSteps.userCreateMail(toWhom, title, mailText, fileLocation);

        //And
        mailNavigationSteps.clickSendButton();

        //Then
        mailNavigationSteps.mailSuccessfullySent();

        //And
        mailNavigationSteps.mailDiplayedInSendPage(title);
    }
}
