package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.dto.JoinRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserInformationRepository {
    private static final UserInformationRepository instance = new UserInformationRepository();
    private static final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();

    private UserInformationRepository(){}

    public static UserInformationRepository getInstance() { return instance; }

    public void insertNewUserInformation(Long createdAccountId, JoinRequest joinRequest) throws SQLException {
        String sql = "INSERT INTO USER_INFORMATIONS_TEST VALUES(?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT)";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, createdAccountId);
            preparedStatement.setString(2, joinRequest.getName());
            preparedStatement.setString(3, joinRequest.getPhoneNumber());
            preparedStatement.setString(4, joinRequest.getEmail());
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }
}
