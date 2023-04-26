package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.entity.UserInformation;

import java.sql.*;


public class UserInformationRepository {
    private static final UserInformationRepository instance = new UserInformationRepository();
    private static final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();

    private UserInformationRepository(){}

    public static UserInformationRepository getInstance() { return instance; }

    public void insertNewUserInformation(UserInformation userInformation) throws SQLException {
        String sql = "INSERT INTO USER_INFORMATIONS_TEST VALUES(?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, userInformation.getAccountId());
            preparedStatement.setString(2, userInformation.getName());
            preparedStatement.setString(3, userInformation.getPhoneNumber());
            preparedStatement.setString(4, userInformation.getEmail());
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }

    public UserInformation findUserInformationById(Long accountId) throws SQLException {
        String sql = "SELECT * FROM USER_INFORMATIONS_TEST";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();){
            resultSet.next();
            String name = resultSet.getString("NAME");
            String phoneNumber = resultSet.getString("PHONE_NUMBER");
            String email = resultSet.getString("EMAIL");
            Timestamp registeredTime = resultSet.getTimestamp("REGISTERED_DATE");
            Timestamp recentConnection = resultSet.getTimestamp("RECENT_CONNECTION");
            Long userGradeId = resultSet.getLong("USER_GRADE_ID");
            return UserInformation.builder()
                .accountId(accountId)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .registeredTime(registeredTime)
                .recentConnection(recentConnection)
                .userGradeId(userGradeId)
                .build();
        }
    }

    public void updateInformation(UserInformation userInformation) throws SQLException {
        String sql = "UPDATE USER_INFORMATIONS_TEST SET NAME = ?, PHONE_NUMBER = ?, EMAIL = ? WHERE ACCOUNT_ID = ?";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, userInformation.getName());
            preparedStatement.setString(2, userInformation.getPhoneNumber());
            preparedStatement.setString(3, userInformation.getEmail());
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }

    public void updateGrade(Long accountId, Long gradeId) throws SQLException {
        String sql = "UPDATE USER_INFORMATIONS_TEST SET USER_GRADE_ID = ? WHERE ACCOUNT_ID = ?";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, gradeId);
            preparedStatement.setLong(2, accountId);
            preparedStatement.executeUpdate();
        }
    }
}
