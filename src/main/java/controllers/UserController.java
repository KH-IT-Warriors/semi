package controllers;

import domains.account.users.UserInformation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UserController {
    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection("url", "id", "pw");
    }


//      "CREATE TABLE USER_INFORMATIONS (
//          ACCOUNT_ID BIGINT,
//          NAME VARCHAR(255) NOT NULL,
//          PHONE_NUMBER VARCHAR(255) NOT NULL,
//          EMAIL VARCHAR(255) NOT NULL,
//          REGISTERED_DATE TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
//          RECENT_CONNECTION TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
//          USER_GRADE_ID BIGINT DEFAULT 1, // 기본 등급 아이디
//          FOREIGN KEY (ACCOUNT_ID) REFERENCES USER_ACCOUNTS (ID) ON DELETE CASCADE,
//          FOREIGN KEY (USER_GRADE_ID) REFERENCES USER_GRADES (ID)
//      );"
    public void insertUserInformation(UserInformation userInformation) throws Exception {
        String sql = "INSERT INTO USER_INFORMATIONS VALUES(?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setLong(1, userInformation.getAccountId());
//            preparedStatement.setString(2, userInformation.getName());
//            preparedStatement.setString(3, userInformation.getPhoneNumber());
//            preparedStatement.setString(4, userInformation.getEmail());
//            preparedStatement.executeUpdate();
//        }
        System.out.println("추가된 사용자 ID : " + userInformation.getAccountId());
        System.out.println("이름 : " + userInformation.getName());
        System.out.println("전화번호 : " + userInformation.getPhoneNumber());
        System.out.println("이메일 : " + userInformation.getEmail());
        System.out.println("가입일시 : " + userInformation.getRegisteredTime());
        System.out.println("최근 접속시간 : " + userInformation.getRecentConnection());
    }
}
