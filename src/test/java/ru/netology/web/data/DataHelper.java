package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {

    }

    public static AuthInfo getAuthInfo() {
        AuthInfo authInfo = new AuthInfo("vasya", "qwerty123");
        return authInfo;
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        VerificationCode verificationCode = new VerificationCode("12345");
        return verificationCode;
    }

    public static CardInfo getFirstCard() {
        String cardNumber = "5559 0000 0000 0001";
        return new CardInfo(cardNumber);
    }

    public static CardInfo getSecondCard() {
        String cardNumber = "5559 0000 0000 0002";
        return new CardInfo(cardNumber);
    }


    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardNumber;
    }
}
