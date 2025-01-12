package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id='code'] input");
    private final SelenideElement buttonContinue = $("[data-test-id='action-verify']");

    public VerificationPage() {
        webdriver().shouldHave(url("http://localhost:9999/verification"));
        $(Selectors.withText("Необходимо подтверждение")).should(Condition.visible);
        codeField.should(Condition.visible);
        buttonContinue.should(Condition.text("Продолжить"), Condition.visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        codeField.setValue(verificationCode.getCode());
        buttonContinue.click();
        return new DashboardPage();
    }
}
