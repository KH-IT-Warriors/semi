package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Profile;

import java.sql.*;
import java.util.Optional;

public class ProfileDao {

    private static final ProfileDao instance = new ProfileDao();

    private ProfileDao() {
    }

    public static ProfileDao getInstance() {
        return instance;
    }

    public Optional<Profile> read(Long id) {
        String sql = "SELECT * FROM profiles WHERE account_id = ?";
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return Optional.of(Profile.of(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
