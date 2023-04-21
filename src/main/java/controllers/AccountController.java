package controllers;

import domains.account.management.AccountInformation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class AccountController {

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection("url", "id", "pw");
    }


//      "CREATE TABLE USER_ACCOUNT (
//          ACCOUNT_ID BIGINT AUTO_INCREMENT,
//          ACCOUNT_STATUS_ID BIGINT,
//          ACCOUNT_ROLE_ID BIGINT,
//          USER_NAME VARCHAR(255) NOT NULL,
//          USER_PW CHAR(256) NOT NULL,
//          PRIMARY KEY (ACCOUNT_ID),
//          FOREIGN KEY (ACCOUNT_STATUS_ID) REFERENCES ACCOUNT_STATUSES (ACCOUNT_STATUS_ID),
//          FOREIGN KEY (ACCOUNT_ROLE_ID) REFERENCES ACCOUNT_ROLES (ACCOUNT_ROLE_ID)
//      );"
    // 계정
    public void insertAccount(AccountInformation accountInformation) throws Exception {
        String sql = "INSERT INTO USER_ACCOUNTS VALUES(0, ?, ?, ?, ?)";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setLong(1, accountInformation.getAccountStatusId());
//            preparedStatement.setLong(2, accountInformation.getAccountRoleId());
//            preparedStatement.setString(3, accountInformation.getUserName());
//            preparedStatement.setString(4, accountInformation.getUserPw());
//            preparedStatement.executeUpdate();
            System.out.println(sql);
            System.out.println("계정 아이디 : "+accountInformation.getAccountStatusId());
            System.out.println("계정 상태 아이디 : " + accountInformation.getAccountStatusId());
            System.out.println("역할 아이디 : " + accountInformation.getAccountRoleId());
            System.out.println("사용자 아이디 : " + accountInformation.getUserName());
            System.out.println("비밀번호 : " + accountInformation.getUserPw());
//        }
    }
    public void updateAccount(AccountInformation accountInformation) throws Exception {
        String sql = "UPDATE USER_ACCOUNTS SET USER_PW = ? WHERE ACCOUNT_ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setString(1, accountInformation.getUserPw());
//            preparedStatement.setLong(2, accountInformation.getAccountId());
//            preparedStatement.executeUpdate();
            System.out.println(sql);
            System.out.printf("비밀번호가 %s로 변경되었습니다.\n", accountInformation.getUserPw());
            System.out.println("변경된 사용자 아이디 : " + accountInformation.getUserName());
            System.out.println("계정 아이디 : " + accountInformation.getAccountId());
//        }
    }

}

