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
            .accountId(100L)
            .accountStatusId(200L)
            .accountRoleId(1L)
            .userName("userId012")
            .userPw(Password.of(100L, "qweR!234"))
            .build();
        accountController.insertAccount(accountInformation1);

        System.out.println("===============");

        AccountInformation accountInformation2 = AccountInformation.builder()
            .accountId(100L)
            .accountStatusId(200L)
            .accountRoleId(1L)
            .userName("userId012")
            .userPw(Password.of(100L, "qweR!234").withPassword("QWer123$"))
            .build();
        accountController.updateAccount(accountInformation2);

        System.out.println("===============");

        AccountInformation accountInformation3 = AccountInformation.builder()
            .accountId(100L)
            .accountStatusId(205L)
            .accountRoleId(5L)
            .userName("userId012")
            .userPw(Password.of(100L, "QWEr123$"))
            .build();
        accountController.deleteAccount(accountInformation3);

        System.out.println("===============");

//        AccountInformation user = accountController.findAccountInformationById(accountInformation1);
//        System.out.println(user);

        System.out.println("===============");

        UserController userController = new UserController();

        UserInformation userInformation1 = UserInformation.builder()
            .accountId(100L)
            .name("장길웅")
            .phoneNumber("010-1234-1234")
            .email("email@email.com")
            .registeredTime(new Timestamp(System.currentTimeMillis()))
            .recentConnection(new Timestamp(System.currentTimeMillis()))
            .userGradeId(901L)
            .build();
        userController.insertUserInformation(userInformation1);

        System.out.println("===============");

        UserInformation userInformation2 = UserInformation.builder()
            .accountId(100L)
            .name("수정함")
            .phoneNumber("010-9876-9876")
            .email("email2@modified.com")
            .registeredTime(new Timestamp(System.currentTimeMillis()))
            .recentConnection(new Timestamp(System.currentTimeMillis()))
            .userGradeId(903L)
            .build();
        userController.updateUserInformation(userInformation2);

        System.out.println("===============");

        UserInformation userInformation3 = UserInformation.builder()
            .accountId(100L)
            .name("장길웅")
            .phoneNumber("000-0000-0000")
            .email("aaa@aaa.com")
            .registeredTime(new Timestamp(System.currentTimeMillis()))
            .recentConnection(new Timestamp(System.currentTimeMillis()))
            .userGradeId(902L)
            .build(); // accountId, name 외에 다른 정보는 중요하지 않음
        userController.deleteUserInformation(userInformation3);
    }
}
