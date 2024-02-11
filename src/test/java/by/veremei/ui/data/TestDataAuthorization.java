package by.veremei.ui.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataAuthorization {
    private final Faker faker = new Faker(new Locale("en"));
    public String buyerIncorrectPass = faker.internet().password(4,8),
            buyerEmail = faker.internet().emailAddress(),
            buyerPass = faker.internet().password(),
            buyerIncorrectLogin = faker.internet().emailAddress("$#@@!");
}
