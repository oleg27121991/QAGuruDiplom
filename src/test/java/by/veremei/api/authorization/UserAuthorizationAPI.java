package by.veremei.api.authorization;

import static com.codeborne.selenide.Selenide.refresh;
import by.veremei.ui.page.MainPage;
import by.veremei.ui.test.TestBase;
import by.veremei.utils.cookies.CookieManagement;

public class UserAuthorizationAPI extends TestBase {
    MainPage mainPage = new MainPage();
    public void authWithRestCookie(String login, String pass) {
        String account_sk = mainPage.loginWithApi(login, pass);
        CookieManagement.addCookie("account_sk", account_sk);
        refresh();
    }
}
