package by.veremei.ui.page;

import by.veremei.ui.helpers.AllureRestAssuredFilter;
import by.veremei.ui.test.TestBase;
import com.codeborne.selenide.*;
import io.restassured.http.ContentType;
import org.openqa.selenium.Cookie;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

public class MainPage extends TestBase {
    private final static String PHPSESSID_COOKIES_NAME = "PHPSESSID";
    private static final String LOGIN_URL = "/account";
    private static final String POST_LOGIN_ORG = "/account/";
    private final SelenideElement buttonLoginBuyerForm = $(".personal-account"),
            inputLoginUser = $("#apollo-login-form input[name='log']"),
            buyerLoginFormDiv = $("div[id='user.enter.enter_account']"),
            titleInBuyerLoginForm = $(".apollo-login h2"),
            linkOpenLanguageList = $(".current-language"),
            inputPasswordUser = $("#apollo-login-form input[name='pass']"),
            buttonLoginUser = $("#apollo-login-form .btn-success"),
            notify = $("#notify");
    private final ElementsCollection listWithLanguage = $$(".lang"),
                                    menuCategoryNavigation = $$(".sub-link");
    public void openMainPage (String url) {
        Selenide.open(url);
    }

    public void openBuyerLoginForm() {
        buttonLoginBuyerForm.click();
    }

    public void checkBuyerLoginFormIsDisplay() {
        buyerLoginFormDiv.shouldBe(visible);
    }

    public void checkTitleInBuyerLoginForm(String title) {
        titleInBuyerLoginForm.shouldHave(text(title));
    }
    public MainPage changeLangFromLanguageLink(Language language) {
        linkOpenLanguageList.click();
        listWithLanguage.findBy(Condition.attribute("onclick", "app.setLang('" + language.cssClass + "')")).click();

        return this;
    }

    public void checkButtonLocalization(List<String> expectedBtn) {
        menuCategoryNavigation.shouldHave(texts(expectedBtn));
    }

    public void loginBuyer(String username, String password) {
        inputLoginUser.sendKeys(username);
        inputPasswordUser.sendKeys(password);
        buttonLoginUser.click();
    }

    public void displayedNotifyIncorrectBuyerLoginOrPassword() {
        notify.shouldBe(visible);
    }

    public String loginWithApi(String email, String password) {
        open(LOGIN_URL);

        String phpSessId = getCookieValue(PHPSESSID_COOKIES_NAME);

        return given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .contentType(ContentType.URLENC)
                .cookie(PHPSESSID_COOKIES_NAME, phpSessId)
                .formParam("process", "user.enter")
                .formParam("action", "active")
                .formParam("log", email)
                .formParam("pass", password)
                .post(POST_LOGIN_ORG)
                .then()
                .statusCode(200)
                .log().all().extract().cookie("account_sk");
    }

    private static String getCookieValue(String cookieName) {
        Cookie cookie = WebDriverRunner.getWebDriver().manage().getCookieNamed(cookieName);

        return cookie.getValue();
    }

    public void openCategory(String nameCategory) {
        $(byTagAndText("a", nameCategory)).click(ClickOptions.usingJavaScript());
    }
}
