package by.veremei.ui.test;

import by.veremei.ui.config.ConfigReader;
import by.veremei.ui.config.web.WebConfig;
import by.veremei.ui.data.TestDataAuthorization;
import by.veremei.ui.page.BuyerAccountPage;
import by.veremei.ui.page.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static io.qameta.allure.Allure.step;

@DisplayName("Авторизация")
@Tag("AUTH")
public class AuthorizationTest extends TestBase {
    MainPage mainPage = new MainPage();
    TestDataAuthorization authorizationData = new TestDataAuthorization();
    BuyerAccountPage buyerAccountPage = new BuyerAccountPage();
    private String buyerName;
    private String buyerPass;

    @BeforeEach
    void setUp() {
        WebConfig config = ConfigReader.Instance.read();
        buyerName = config.buyerName();
        buyerPass = config.buyerPass();
    }

    @Test
    @Feature("Авторизация покупателя")
    @Story("Успешная авторизация")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("AUTH")
    @DisplayName("Успешная авторизация зарегистрированного покупателя")
    void testUserValidAuthorization() {
        step("Открываем главную страницу", () ->
                mainPage.openMainPage(baseUrl)
        );
        step("Открываем форму авторизации покупателя", () ->
                mainPage.openBuyerLoginForm()
        );
        step("Вводим правильные данные зарегистрированного покупателя", () ->
                mainPage.loginBuyer(buyerName, buyerPass)
        );
        step("Проверяем что покупатель успешно авторизован в личном кабинете", () ->
                buyerAccountPage.checkBuyerName(buyerName)
        );
    }

    @Test
    @Feature("Авторизация покупателя")
    @Story("Ошибка авторизации")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.NORMAL)
    @Tag("AUTH")
    @DisplayName("Авторизация покупателя с неверным паролем")
    void testUserIncorrectPasswordAuthorization() {
        step("Открываем главную страницу", () ->
                mainPage.openMainPage(baseUrl)
        );
        step("Открываем форму авторизации покупателя", () ->
                mainPage.openBuyerLoginForm()
        );
        step("Вводим правильный Login и неправильный Password зарегистрированного покупателя", () ->
                mainPage.loginBuyer(buyerName, authorizationData.buyerIncorrectPass)
        );
        step("Проверяем наличие сообщения об неправильном логине или пароле", () ->
                mainPage.displayedNotifyIncorrectBuyerLoginOrPassword()
        );
    }

    @Test
    @Feature("Авторизация покупателя")
    @Story("Ошибка авторизации")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.NORMAL)
    @Tag("AUTH")
    @DisplayName("Авторизация покупателя с неверным логином")
    void testUserIncorrectLoginAuthorization() {
        step("Открываем главную страницу", () ->
                mainPage.openMainPage(baseUrl)
        );
        step("Открываем форму авторизации покупателя", () ->
                mainPage.openBuyerLoginForm()
        );
        step("Вводим правильный Login и неправильный Password зарегистрированного покупателя", () ->
                mainPage.loginBuyer(authorizationData.buyerIncorrectLogin, buyerPass)
        );
        step("Проверяем наличие сообщения об неправильном логине или пароле", () ->
                mainPage.displayedNotifyIncorrectBuyerLoginOrPassword()
        );
    }
}
