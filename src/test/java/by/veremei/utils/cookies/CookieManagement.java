package by.veremei.utils.cookies;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.Date;

public class CookieManagement {
    private final static String DOMAIN_URL = "bezkassira.by";
    public static void addCookie(String name, String value) {
        Date expDate = new Date();
        expDate.setTime(expDate.getTime() + (10000*10000));

        Cookie cookie = new Cookie(name, value, DOMAIN_URL, "/", expDate);
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
    }
}
