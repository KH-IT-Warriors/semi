package kr.co.khacademy.semi;

import controllers.AccountController;
import controllers.UserController;
import domains.account.commons.Password;
import domains.account.management.AccountInformation;
import domains.account.users.UserInformation;

import java.sql.Timestamp;

public class Main {
    public static void main(String[] args) throws Exception {
        AccountController accountController = new AccountController();

        AccountInformation accountInformation1 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(200)
            .accountRoleId(1)
            .userName("userId012")
            .userPw(Password.of(100, "qweR!234"))
            .build();
        accountController.insertAccount(accountInformation1);

        System.out.println("===============");

        AccountInformation accountInformation2 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(200)
            .accountRoleId(1)
            .userName("userId012")
            .userPw(Password.of(100, "qweR!234").withPassword("QWer123$"))
            .build();
        accountController.updateAccount(accountInformation2);

        System.out.println("===============");

        AccountInformation accountInformation3 = AccountInformation.builder()
            .accountId(100)
            .accountStatusId(205)
            .accountRoleId(5)
            .userName("userId012")
            .userPw(Password.of(100, "QWEr123$"))
            .build();
        accountController.deleteAccount(accountInformation3);

        System.out.println("===============");

//        AccountInformation user = accountController.findAccountInformationById(accountInformation1);
//        System.out.println(user);

        System.out.println("===============");

        UserController userController = new UserController();

        UserInformation userInformation1 = UserInformation.builder()
            .accountId(100)
            .name("장길웅")
            .phoneNumber("010-1234-1234")
            .email("email@email.com")
            .registeredTime(new Timestamp(System.currentTimeMillis()))
            .recentConnection(new Timestamp(System.currentTimeMillis()))
            .build();
        userController.insertUserInformation(userInformation1);
    }
}
