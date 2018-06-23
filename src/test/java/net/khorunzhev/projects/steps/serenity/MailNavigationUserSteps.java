package net.khorunzhev.projects.steps.serenity;

import net.khorunzhev.projects.pages.AuthorizationPage;
import net.khorunzhev.projects.pages.mail.ComposeMailPage;
import net.khorunzhev.projects.pages.mail.InboxMailPage;
import net.khorunzhev.projects.pages.mail.SendMailPage;
import net.khorunzhev.projects.pages.mail.SendStatusPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

/**
 * Created by khorunzhev on 22.06.2018.
 */
public class MailNavigationUserSteps {

    AuthorizationPage authorizationPage;
    ComposeMailPage composeMailPage;
    InboxMailPage inboxMailPage;
    SendMailPage sendMailPage;
    SendStatusPage sendStatusPage;


    @Step
    public void userOpenAutorizationPage() {
        authorizationPage.open();
        authorizationPage.checkLoginButtonIsVisible();
    }

    @Step
    public void userAuthorized(String login, String password) {
        authorizationPage.loginToMail(login, password);
    }

    @Step
    public void mainMailPageOpen() {
        inboxMailPage.isMainMailPageOpen();
        inboxMailPage.checkActionsOnInboxPage(new String[]{"Удалить", "Спам", "Переместить", "Ещё"});
    }

    @Step
    public void userClickWriteMailButton() {
        inboxMailPage.clickComposeMailButton();
    }

    public void composeMailPageOpen() {
        composeMailPage.checkActionsOnComposePage(new String[]{"Отправить", "Сохранить", "Отмена"});
    }

    @Step
    public void userCreateMail(String toWhom, String title, String mailText, String fileLocation) {
        composeMailPage.setToWhomInput(toWhom);
        composeMailPage.setTitleInput(title);
        composeMailPage.setMailTextInput(mailText);
        composeMailPage.uploadFile(fileLocation);
    }

    @Step
    public void clickSendButton() {
        composeMailPage.clickMailSendButton();
    }

    @Step
    public void mailSuccessfullySent() {
        Assert.assertTrue(sendStatusPage.iSMailSent());
    }

    @Step
    public void mailDiplayedInSendSection(String title) {
        composeMailPage.goToOutBoxList();
        sendMailPage.checkFirstElementInMailList(title);


    }
}
