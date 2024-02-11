package by.veremei.ui.test;

import by.veremei.ui.page.EventPage;
import by.veremei.ui.page.MainPage;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static io.qameta.allure.Allure.step;

@DisplayName("Мероприятие")
@Tag("EVENTS")
public class EventsTest extends TestBase {
    MainPage mainPage = new MainPage();
    EventPage eventPage = new EventPage();
    private static final String NAME_EVENT_CATEGORIES = "Концерты";

    @Test
    @Feature("Мероприятия")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.MINOR)
    @Tag("EVENTS")
    @DisplayName("Проверка отображения возрастного ограничения на странице мероприятия")
    void checkAgeLimitOnPageEvent() {
        step("Открываем главную страницу", () ->
            mainPage.openMainPage(baseUrl)
        );
        step("Переходим в категорию 'Концерты'", () ->
            mainPage.openCategory(NAME_EVENT_CATEGORIES)
        );
        step("Открываем случайное мероприятие", () ->
            eventPage.openRandomEvent()
        );
        step("Проверяем наличие иконки с возрастным ограничением на странице с описанием мероприятия", () ->
            eventPage.checkAgeLimitIcon()
        );
    }

    @Test
    @Feature("Мероприятия")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.MINOR)
    @Tag("EVENTS")
    @DisplayName("Проверка отображения стоимости билета на странице мероприятия")
    void checkTicketCostOnPageEvent() {
        step("Открываем главную страницу", () ->
                mainPage.openMainPage(baseUrl)
        );
        step("Переходим в категорию 'Концерты'", () ->
                mainPage.openCategory(NAME_EVENT_CATEGORIES)
        );
        step("Открываем последнее мероприятие", () ->
                eventPage.openLastEvent()
        );
        step("Проверяем наличие текста со стоимостью билета на мероприятие", () ->
                eventPage.checkTicketCostText()
        );
    }
}
