package by.veremei.ui.test;

import by.veremei.api.authorization.UserAuthorizationAPI;
import by.veremei.ui.config.ConfigReader;
import by.veremei.ui.config.web.WebConfig;
import by.veremei.ui.page.EventPage;
import by.veremei.ui.page.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static com.codeborne.selenide.Configuration.baseUrl;
import static io.qameta.allure.Allure.step;

public class BuyTicketTest extends TestBase {
    private String buyerName;
    private String buyerPass;

    @BeforeEach
    void setUp() {
        WebConfig config = ConfigReader.Instance.read();
        buyerName = config.buyerName();
        buyerPass = config.buyerPass();
    }
    UserAuthorizationAPI userAuthAPI = new UserAuthorizationAPI();
    MainPage mainPage = new MainPage();
    EventPage eventPage = new EventPage();

    private final static String NAME_EVENT_CATEGORIES = "Концерты";

    @Test
    @Feature("Покупка билетов")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.NORMAL)
    @Tag("TICKETS")
    @DisplayName("Проверка отображения общей стоимости выбранных билетов")
    void checkTicketCostOnPageWhenPlacingAnOrder() {
        AtomicReference<String> count = new AtomicReference<>("");
        AtomicReference<String> price = new AtomicReference<>("");
        step("Авторизуемся как покупатель через API", () ->
            userAuthAPI.authWithRestCookie(buyerName, buyerPass)
        );
        step("Открываем главную страницу", () ->
            mainPage.openMainPage(baseUrl)
        );
        step("Переходим в категорию 'Концерты'", () ->
            mainPage.openCategory(NAME_EVENT_CATEGORIES)
        );
        step("Открываем последнее мероприятие", () ->
            eventPage.openLastEvent()
        );
        step("Переходим на страницу выбора билетов", () ->
            eventPage.clickBuyTicketBtnOnDescrEventPage()
        );
        step("Записываем цену на билет в переменную", () ->
            price.set(eventPage.getPriceWithoutCurrency())
        );
        step("Выбираем случайное количество билетов на мероприятие", () -> {
            eventPage.selectRandomTicketCount();
            count.set(eventPage.getTicketCount());
        });
        step("Проверка правильности расчета сервисного сбора и общей стоимости билетов", () ->
            eventPage.checkTotalCostAndServiceFee(price.get(), count.get())
        );
    }
}
