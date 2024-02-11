package by.veremei.ui.page;

import by.veremei.ui.page.components.CheckServiceFeeAndTotalCostComponent;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.Random;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class EventPage {
    CheckServiceFeeAndTotalCostComponent checkServiceFeeAndTotalCostComponent = new CheckServiceFeeAndTotalCostComponent();
    private final ElementsCollection linkOpenEventDetails = $$(".afisha_image");
    private final SelenideElement divAgeLimit = $(".sign_age"),
                                  divWithTicketCostText = $(".data-activity .pull-right");
    public void openRandomEvent() {
        Random random = new Random();
        int randomIndex = random.nextInt(linkOpenEventDetails.size());
        linkOpenEventDetails.get(randomIndex).click();
    }

    public void openLastEvent() {
        linkOpenEventDetails.last().click();
    }

    public void checkAgeLimitIcon() {
        divAgeLimit.shouldBe(visible);
    }

    public void checkTicketCostText() {
        divWithTicketCostText.shouldNotBe(empty);
    }

    private final SelenideElement linkBuyTicket = $(".buy-button a");
    public void clickBuyTicketBtnOnDescrEventPage() {
        linkBuyTicket.click();
    }

    private final SelenideElement priceElement = $(".price-info");

    public String getPriceWithoutCurrency() {
        String priceText = priceElement.getText().replaceAll("[^\\d.]+", "");

        return priceText;
    }

    private final SelenideElement ticketSelect = $(".group_info_ticket");

    public void selectRandomTicketCount() {
        int optionsCount = ticketSelect.$$("option").size();
        int randomIndex = (int) (Math.random() * optionsCount);
        ticketSelect.selectOption(randomIndex);
    }

    public String getTicketCount() {
        String count = ticketSelect.getText().replaceAll("[^\\d.]+", "");

        return count;
    }

    public void checkTotalCostAndServiceFee(String valuePrice, String valueCount) {
        checkServiceFeeAndTotalCostComponent.checkServiceFeeAndCostTicket(valuePrice, valueCount);
    }
}
