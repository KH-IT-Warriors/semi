package kr.co.khacademy.semi;

import controllers.AccountController;
import domains.account.commons.Password;
import domains.account.management.AccountInformation;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountController accountController = new AccountController();

        AccountInformation accountInformation1 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(200)
            .accountRoleId(1)
            .userName("길웅")
            .userPw(Password.of(100, "qweR!234"))
            .build();
        accountController.insertAccount(accountInformation1);

        System.out.println("===============");

        AccountInformation accountInformation2 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(200)
            .accountRoleId(1)
            .userName("길웅")
            .userPw(Password.of(100, "qweR!234").withPassword("QWer123$"))
            .build();
        accountController.updateAccount(accountInformation2);

        System.out.println("===============");

        AccountInformation accountInformation3 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(205)
            .accountRoleId(5)
            .userName("길웅")
            .userPw(Password.of(100, "QWEr123$"))
            .build();
        accountController.deleteAccount(accountInformation3);

        System.out.println("===============");

        AccountInformation user = accountController.findAccountInformationById(accountInformation1);
        System.out.println(user);
    }
}
