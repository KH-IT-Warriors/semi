package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Profile;

import java.sql.*;

public class ProfileDao {

    private static final ProfileDao instance = new ProfileDao();

    private ProfileDao() {
    }

    public static ProfileDao getInstance() {
        return instance;
    }

    public Profile read(Long id) {
        String sql = "SELECT * FROM profiles WHERE account_id = ?";
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()) {
            return Profile.of(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
