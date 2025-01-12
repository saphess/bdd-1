package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    private final DataHelper.AuthInfo userInfo = DataHelper.getAuthInfo();
    private final DataHelper.VerificationCode verifyCode = DataHelper.getVerificationCode(userInfo);
    private final DataHelper.CardInfo firstCardNumber = DataHelper.getFirstCard();
    private final DataHelper.CardInfo secondCardNumber = DataHelper.getSecondCard();

    @BeforeEach
    public void validAuthorization() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validAuth(userInfo);
        var initialDashboardPage = verificationPage.validVerify(verifyCode);
    }


    @Test
    public void transferFromSecondCardToTheFirstCardTest() {
        var initialDashboardPage = new DashboardPage();
        int initialFirstCardBalance = initialDashboardPage.getFirstCardBalance();
        int initialSecondCardBalance = initialDashboardPage.getSecondCardBalance();

        var payOutFirstCardPage = initialDashboardPage.payOutFirstCard();
        var dashboardPage = payOutFirstCardPage.transferMoney(200, secondCardNumber.getCardNumber());

        int actualFirstCard = dashboardPage.getFirstCardBalance();
        int expectFirstCard = initialFirstCardBalance + 200;

        int actualSecondCard = dashboardPage.getSecondCardBalance();
        int expectSecondCard = initialSecondCardBalance - 200;

        assertEquals(expectFirstCard, actualFirstCard);
        assertEquals(expectSecondCard, actualSecondCard);
    }

    @Test
    public void transferFromFirstCardToTheSecondCard() {
        var initialDashboardPage = new DashboardPage();
        int initialFirstCardBalance = initialDashboardPage.getFirstCardBalance();
        int initialSecondCardBalance = initialDashboardPage.getSecondCardBalance();

        var payOutSecondCardPage = initialDashboardPage.payOutSecondCard();
        var dashboardPage = payOutSecondCardPage.transferMoney(200, firstCardNumber.getCardNumber());

        int actualFirstCard = dashboardPage.getFirstCardBalance();
        int expectFirstCard = initialFirstCardBalance - 200;

        int actualSecondCard = dashboardPage.getSecondCardBalance();
        int expectSecondCard = initialSecondCardBalance + 200;

        assertEquals(expectFirstCard, actualFirstCard);
        assertEquals(expectSecondCard, actualSecondCard);
    }
}
