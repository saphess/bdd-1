package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;

public class PayOutCard {
    private final SelenideElement errorNotification = $("[data-test-id='error-notification']");
    private final SelenideElement dashboardLabel = $("[data-test-id='dashboard']");
    private final SelenideElement headerLabel = $(Selectors.withText("Пополнение карты"));
    private final SelenideElement amountField = $("[data-test-id='amount'] input");
    private final SelenideElement fromCardField = $("[data-test-id='from'] input");
    private final SelenideElement toCardField = $("[data-test-id='to'] input");
    private final SelenideElement continueButton = $("[data-test-id='action-transfer']");
    private final SelenideElement cancelButton = $("[data-test-id='action-cancel']");

    public PayOutCard() {
        dashboardLabel.shouldHave(Condition.text("Личный кабинет"), Condition.visible);
        headerLabel.shouldHave(Condition.visible);
        amountField.shouldHave(Condition.visible);
        fromCardField.shouldHave(Condition.visible);
        toCardField.shouldHave(Condition.visible);
    }

    private void cleanFields() {
        amountField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        fromCardField.sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    public DashboardPage transferMoney(int amount, String cardNumber) {
        cleanFields();
        amountField.setValue(String.valueOf(amount));
        fromCardField.setValue(cardNumber);
        continueButton.click();

        return new DashboardPage();
    }
}
