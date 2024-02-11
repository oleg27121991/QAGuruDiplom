package by.veremei.ui.page.components;

import com.codeborne.selenide.SelenideElement;

import java.text.DecimalFormat;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckServiceFeeAndTotalCostComponent {
    private final  SelenideElement divWithOrderInfo = $(".order-info");
    public void checkServiceFeeAndCostTicket(String price, String count) {
        divWithOrderInfo.shouldBe(visible);
        divWithOrderInfo.scrollTo();
        double ticketPrice = Double.parseDouble(price);
        int ticketCount = Integer.parseInt(count);

        double serviceFee = 0.0;
        for (int i = 1; i <= ticketCount; i++) {
            serviceFee = ticketPrice * 0.0613;
            if (serviceFee > 5.0) {
                serviceFee = 5.0;
            } else if (serviceFee < 0.8) {
                serviceFee = 0.8;
            }
        }
        double totalCost = (ticketPrice + serviceFee) * ticketCount;
        double roundedCost = Math.round(totalCost * 20.0) / 20.0;
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        String formattedCost = decimalFormat.format(roundedCost);
        formattedCost = formattedCost.replace(",", " ").replace(".", ",").replace(" ", ".");

        divWithOrderInfo.shouldHave(text(formattedCost))
                .shouldHave(text(String.valueOf(ticketCount)));
    }
}
