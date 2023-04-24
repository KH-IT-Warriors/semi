package controllers;

import domains.account.users.UserInformation;
import kr.co.khacademy.semi.conf.MySqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserController {

    private final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();

//      "CREATE TABLE USER_INFORMATIONS_TEST (
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
        String sql = "INSERT INTO USER_INFORMATIONS_TEST VALUES(?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);){
            preparedStatement.setLong(1, userInformation.getAccountId());
            preparedStatement.setString(2, userInformation.getName());
            preparedStatement.setString(3, userInformation.getPhoneNumber());
            preparedStatement.setString(4, userInformation.getEmail());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("추가된 사용자 ID : " + userInformation.getAccountId());
            System.out.println("이름 : " + userInformation.getName());
            System.out.println("전화번호 : " + userInformation.getPhoneNumber());
            System.out.println("이메일 : " + userInformation.getEmail());
            System.out.println("가입일시 : " + userInformation.getRegisteredTime());
            System.out.println("최근 접속시간 : " + userInformation.getRecentConnection());
            System.out.println("멤버십 등급 : " + userInformation.getUserGradeId());
//            try(ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
//                resultSet.next();
//                long result = resultSet.getLong(1);
//                return result;
//            }
        }
    }

    public void updateUserInformation(UserInformation userInformation) throws Exception {
        String sql = "UPDATE USER_INFORMATIONS SET NAME = ?, PHONE_NUMBER = ?, EMAIL = ? USER_GRADE_ID = ? WHERE ACCOUNT_ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setString(1, userInformation.getName());
//            preparedStatement.setString(2, userInformation.getPhoneNumber());
//            preparedStatement.setString(3, userInformation.getEmail());
//            preparedStatement.setLong(4, userInformation.getUserGradeId());
//            preparedStatement.setLong(5, userInformation.getAccountId());
//            preparedStatement.executeUpdate();
//        }
        System.out.println("변경된 사용자 ID : " + userInformation.getAccountId());
        System.out.println("변경된 이름 : " + userInformation.getName());
        System.out.println("변경된 전화번호 : " + userInformation.getPhoneNumber());
        System.out.println("변경된 이메일 : " + userInformation.getEmail());
        System.out.println("변경된 멤버십 등급 : " + userInformation.getUserGradeId());
    }

    public void deleteUserInformation(UserInformation userInformation) throws Exception {
        String sql = "DELETE FROM USER_INFORMATIONS WHERE ACCOUNT_ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setLong(1, userInformation.getAccountId());
//            preparedStatement.executeUpdate();
//        }
        System.out.printf("%s 님의 개인정보가 삭제되었습니다.", userInformation.getName());
    }
}
