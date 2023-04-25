package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.entity.UserInformation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
}
