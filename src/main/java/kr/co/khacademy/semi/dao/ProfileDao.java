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

    private static final String SELECT_PROFILE_SQL =
        "SELECT * FROM accounts WHERE id = ?";
    private static final String UPDATE_PROFILE_SQL =
        "UPDATE profiles SET phone_number = ?, email = ? WHERE account_id = ?";

    public Optional<Profile> read(Long id) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PROFILE_SQL)) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                return Optional.of(Profile.of(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public boolean update(Profile profile) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_SQL)) {
            preparedStatement.setString(1, profile.getPhoneNumber());
            preparedStatement.setString(2, profile.getEmail());
            preparedStatement.setLong(3, profile.getAccountId());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
        }

    }
}
