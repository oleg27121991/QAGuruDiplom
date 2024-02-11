package by.veremei.ui.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class BuyerAccountPage {
    private final SelenideElement buyerNameInAccount = $(".organise-info");
    public void checkBuyerName(String name) {
        buyerNameInAccount.shouldHave(Condition.text(name));
    }
}
