package controllers;

import domains.account.commons.Password;
import domains.account.management.AccountInformation;

import java.sql.*;
import java.util.List;

public class AccountController {

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection("url", "id", "pw");
    }


//      "CREATE TABLE USER_ACCOUNTS (
//          ID BIGINT AUTO_INCREMENT,
//          ACCOUNT_STATUS_ID BIGINT,
//          ACCOUNT_ROLE_ID BIGINT,
//          USER_NAME VARCHAR(255) NOT NULL,
//          USER_PW CHAR(256) NOT NULL,
//          PRIMARY KEY (ACCOUNT_ID),
//          FOREIGN KEY (ACCOUNT_STATUS_ID) REFERENCES ACCOUNT_STATUSES (ACCOUNT_STATUS_ID) ON DELETE CASCADE,
//          FOREIGN KEY (ACCOUNT_ROLE_ID) REFERENCES ACCOUNT_ROLES (ACCOUNT_ROLE_ID) ON DELETE CASCADE
//      );"
    // 계정
    public void insertAccount(AccountInformation accountInformation) throws Exception {
        String sql = "INSERT INTO USER_ACCOUNTS VALUES(0, ?, ?, ?, ?)";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);){
//            preparedStatement.setLong(1, accountInformation.getAccountStatusId());
//            preparedStatement.setLong(2, accountInformation.getAccountRoleId());
//            preparedStatement.setString(3, accountInformation.getUserName());
//            preparedStatement.setString(4, accountInformation.getUserPw().toString());
//            preparedStatement.executeUpdate();
//        }
            System.out.println(sql);
            System.out.println("계정 아이디 : "+accountInformation.getAccountId());
            System.out.println("계정 상태 아이디 : " + accountInformation.getAccountStatusId());
            System.out.println("역할 아이디 : " + accountInformation.getAccountRoleId());
            System.out.println("사용자 아이디 : " + accountInformation.getUserName());
            System.out.println("비밀번호 : " + accountInformation.getUserPw());
    }
    public void updateAccount(AccountInformation accountInformation) throws Exception {
        String sql = "UPDATE USER_ACCOUNTS SET USER_PW = ? WHERE ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setString(1, accountInformation.getUserPw().withPassword("변경할 Pw"));
//            preparedStatement.setLong(2, accountInformation.getAccountId());
//            preparedStatement.executeUpdate();
//        }
            System.out.println(sql);
            System.out.printf("비밀번호가 %s로 변경되었습니다.\n", accountInformation.getUserPw());
            System.out.println("변경된 사용자 아이디 : " + accountInformation.getUserName());
            System.out.println("계정 아이디 : " + accountInformation.getAccountId());
    }

    public void deleteAccount(AccountInformation accountInformation) throws Exception {
        String sql = "UPDATE USER_ACCOUNTS SET ACCOUNT_STATUS = ? WHERE ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setLong(1, 2L); // 삭제대기 상태 아이디
//            preparedStatement.setLong(2, accountInformation.getAccountId());
//            preparedStatement.executeUpdate();
//        }
            System.out.println(sql);
            System.out.printf("계정명 %s가 삭제 대기 상태로 전환 되었습니다.", accountInformation.getUserName());
    }

    // 테이블이 실존하지 않아 테스트 불가능
//    public AccountInformation findAccountInformationById(AccountInformation accountInformation) throws Exception {
//        String sql = "SELECT * FROM USER_ACCOUNTS WHERE ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setLong(1, accountInformation.getAccountId());
//            try(ResultSet resultSet = preparedStatement.executeQuery();){
//                if (resultSet.next()) {
//                    long accountId = resultSet.getLong("ID");
//                    long accountStatusId = resultSet.getLong("ACCOUNT_STATUS_ID");
//                    long accountRoleId = resultSet.getLong("ACCOUNT_ROLE_ID");
//                    String userName = resultSet.getString("USER_NAME");
//                    String userPw = resultSet.getString("USER_PW");
//                    return AccountInformation.builder()
//                        .accountId(accountId)
//                        .accountStatusId(accountStatusId)
//                        .accountRoleId(accountRoleId)
//                        .userName(userName)
//                        .userPw(Password.of(accountId, userPw))
//                        .build();
//                } else {
//                    throw new RuntimeException();
////                    throw new UserNotFoundException("사용자 아이디가 일치하지 않습니다.");
//                }
//            }
//        }
//    }
}

