package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static java.lang.Integer.parseInt;

public class DashboardPage {
    private final SelenideElement dashboardLabel = $("[data-test-id='dashboard']");
    private final SelenideElement firstCard = $$(".list__item").get(0);
    private final SelenideElement secondCard = $$(".list__item").get(1);


    public DashboardPage() {
        webdriver().shouldHave(url("http://localhost:9999/dashboard"));
        dashboardLabel.shouldHave(Condition.text("Личный кабинет"), Condition.visible);
        firstCard.should(Condition.visible);
        secondCard.should(Condition.visible);
    }

    private int getCardBalance(String text) {
        String balanceStart = "баланс: ";
        String balanceEnd = " р.";
        int start = text.indexOf(balanceStart);
        int end = text.indexOf(balanceEnd);

        return Integer.parseInt(text.substring(start + balanceStart.length(), end));
    }

    public int getFirstCardBalance() {
        return getCardBalance(firstCard.text().strip());
    }

    public int getSecondCardBalance() {
        return getCardBalance(secondCard.text().strip());
    }

    public PayOutCard payOutFirstCard(){
        firstCard.find(By.cssSelector("[data-test-id='action-deposit']")).click();
        return new PayOutCard();
    }

    public PayOutCard payOutSecondCard(){
        secondCard.find(By.cssSelector("[data-test-id='action-deposit']")).click();
        return new PayOutCard();
    }
}
