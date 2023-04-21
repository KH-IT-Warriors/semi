package kr.co.khacademy.semi;

import controllers.AccountController;
import domains.account.management.AccountInformation;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountInformation accountInformation1 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(200)
            .accountRoleId(5)
            .userName("길웅")
            .userPw("123")
            .build();
        AccountController accountController = new AccountController();
        accountController.insertAccount(accountInformation1);

        AccountInformation accountInformation2 = AccountInformation.builder()
            .accountId(123)
            .accountStatusId(200)
            .accountRoleId(5)
            .userName("길웅")
            .userPw("456")
            .build();
        accountController.updateAccount(accountInformation2);
    }
}
