package net.khorunzhev.projects.steps.serenity;

import net.khorunzhev.projects.pages.AuthorizationPage;
import net.khorunzhev.projects.pages.mail.ComposeMailPage;
import net.khorunzhev.projects.pages.mail.InboxMailPage;
import net.khorunzhev.projects.pages.mail.SendMailPage;
import net.khorunzhev.projects.pages.mail.SendStatusPage;
import net.thucydides.core.annotations.Step;
import org.junit.Assert;

import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getPages;

/**
 * Created by khorunzhev on 22.06.2018.
 */
public class MailNavigationUserSteps {

    AuthorizationPage authorizationPage;
    ComposeMailPage composeMailPage;
    InboxMailPage inboxMailPage;
    SendMailPage sendMailPage;
    SendStatusPage sendStatusPage;


    @Step("Дано Пользователь находится на странице авторизации mail.ru ")
    public void userOpenAutorizationPage() {
        authorizationPage.open();
        authorizationPage.checkLoginButtonIsVisible();
    }

    @Step("Когда пользователь авторизовывается с логином {0} и паролем {1}")
    public void userAuthorized(String login, String password) {
        authorizationPage.loginToMail(login, password);
        Assert.assertTrue(inboxMailPage.isUserAutorized(login));
    }

    @Step("То открывается страница авторизации")
    public void mainMailPageOpen() {
        inboxMailPage.checkThatInboxMailPageOpen();
    }

    @Step("Когда пользователь нажимает написать письмо")
    public void userClickWriteMailButton() {
        inboxMailPage.clickComposeMailButton();
    }

    @Step("То открывается страница авторизации")
    public void composeMailPageOpen() {
        composeMailPage.checkThatComposeMailPageOpen();
    }

    @Step("Когда пользователь создает письмо с адресатом {0}, заголовком {1}, текстом {2} и файлом {3}")
    public void userCreateMail(String toWhom, String title, String mailText, String fileLocation) {
        composeMailPage.setToWhomInput(toWhom);
        composeMailPage.setTitleInput(title);
        composeMailPage.setMailTextInput(mailText);
        composeMailPage.uploadFile(fileLocation);
    }

    @Step("И нажимает отправить")
    public void clickSendButton() {
        composeMailPage.clickMailSendButton();
    }

    @Step("То открывается страница с успешным статусом отправления")
    public void mailSuccessfullySent() {
        sendStatusPage.checkThatSendStatusMailPageOpen();
        Assert.assertTrue(sendStatusPage.iSMailSent());
    }

    @Step("И письмо с заголовком {0} отображается в списке отправленных")
    public void mailDiplayedInSendPage(String title) {
        composeMailPage.goToOutBoxList();
        sendMailPage.checkThatSendMailPageOpen();
        sendMailPage.checkFirstElementInMailList(title);
    }
}
