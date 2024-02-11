package by.veremei.ui.test;

import by.veremei.ui.page.Language;
import by.veremei.ui.page.MainPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Configuration.baseUrl;
import static io.qameta.allure.Allure.step;

public class MainPageTest extends TestBase {
    MainPage mainPage = new MainPage();

    private final static String TITLE_IN_BUYER_LOGIN_FORM = "Вход для покупателя";

    @Test
    @Feature("Главная страница")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("MAIN")
    @DisplayName("Проверка отображения формы для авторизации покупателя")
    void checkBuyerLoginFormDisplay() {
        step("Открываем главную страницу", () ->
            mainPage.openMainPage(baseUrl)
        );
        step("Открываем форму авторизации", () ->
            mainPage.openBuyerLoginForm()
        );
        step("Проверяем наличие формы авторизации покупателя", () ->
            mainPage.checkBuyerLoginFormIsDisplay()
        );
        step("Проверяем, заголовок формы авторизации покупателя", () ->
            mainPage.checkTitleInBuyerLoginForm(TITLE_IN_BUYER_LOGIN_FORM)
        );
    }

    static Stream<Arguments> checkEventsNameInNavMenu() {
        return Stream.of(
                Arguments.of(
                        Language.BY,
                        List.of("Новы год", "Канцэрты", "Кіно", "Спектаклі", "Бізнес", "ІТ і інтэрнэт", "Спорт", "Для дзяцей", "Вечарынкі", "Фестываль", "Выстава", "Прыгажосць і Здароўе", "Адукацыя і развіццё", "Квэсты і квізы", "Анлайн", "Мастацтва і культура", "Ежа", "Псіхалогія і самапазнанне", "Сертыфікат", "Экскурсіі і падарожжы", "Іншыя падзеі", "Іншыя забавы")
                ),
                Arguments.of(
                        Language.EN,
                        List.of("New Year", "Concerts", "Cinema", "Performances", "Business", "IT and Internet", "Sports", "For children", "Party", "Festivals", "Exhibition", "Beauty and health", "Education and development", "Quests and quizzes", "On-line", "Art and culture", "Food", "Psychology and self-knowledge", "Certificates", "Tours and travels", "Other events", "Other entertainment")
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    @Feature("Главная страница")
    @Owner("tg - @Veremeioleg")
    @Severity(SeverityLevel.BLOCKER)
    @Tag("MAIN")
    @DisplayName("Проверка отображения названий типов мероприятия в навигационном меню")
    void checkEventsNameInNavMenu(Language language, List<String> expectedBtn) {
        step("Открываем главную страницу", () ->
            mainPage.openMainPage(baseUrl)
        );
        step("Меняем язык нажатием кнопки 'Выбор языка'", () ->
                mainPage.changeLangFromLanguageLink(language)
        );
        step("Проверяем локализацию кнопок меню на главной странице", () ->
            mainPage.checkButtonLocalization(expectedBtn)
        );
    }
}
