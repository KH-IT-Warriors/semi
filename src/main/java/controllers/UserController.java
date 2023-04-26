package controllers;

import kr.co.khacademy.semi.entity.Profile;
import kr.co.khacademy.semi.conf.MySqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
    public void insertUserInformation(Profile profile) throws Exception {
        String sql = "INSERT INTO USER_INFORMATIONS_TEST VALUES(?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);){
            preparedStatement.setLong(1, profile.getAccountId());
            preparedStatement.setString(2, profile.getName());
            preparedStatement.setString(3, profile.getPhoneNumber());
            preparedStatement.setString(4, profile.getEmail());
            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("추가된 사용자 ID : " + profile.getAccountId());
            System.out.println("이름 : " + profile.getName());
            System.out.println("전화번호 : " + profile.getPhoneNumber());
            System.out.println("이메일 : " + profile.getEmail());
            System.out.println("가입일시 : " + profile.getRegisteredTime());
            System.out.println("최근 접속시간 : " + profile.getRecentConnection());
            System.out.println("멤버십 등급 : " + profile.getUserGradeId());
//            try(ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
//                resultSet.next();
//                long result = resultSet.getLong(1);
//                return result;
//            }
        }
    }

    public void updateUserInformation(Profile profile) throws Exception {
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
        System.out.println("변경된 사용자 ID : " + profile.getAccountId());
        System.out.println("변경된 이름 : " + profile.getName());
        System.out.println("변경된 전화번호 : " + profile.getPhoneNumber());
        System.out.println("변경된 이메일 : " + profile.getEmail());
        System.out.println("변경된 멤버십 등급 : " + profile.getUserGradeId());
    }

    public void deleteUserInformation(Profile profile) throws Exception {
        String sql = "DELETE FROM USER_INFORMATIONS WHERE ACCOUNT_ID = ?";
//        try(Connection connection = getConnection();
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
//            preparedStatement.setLong(1, userInformation.getAccountId());
//            preparedStatement.executeUpdate();
//        }
        System.out.printf("%s 님의 개인정보가 삭제되었습니다.", profile.getName());
    }
}
