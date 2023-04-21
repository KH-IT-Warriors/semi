package kr.co.khacademy.semi;

import controllers.AccountController;
import domains.account.management.AccountInformation;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountInformation accountInformation = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(200)
            .accountRoleId(5)
            .userName("길웅")
            .userPw("123")
            .build();
        AccountController accountController = new AccountController();
        accountController.insertAccount(accountInformation);
    }
}
