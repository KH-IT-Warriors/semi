package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final UserDao instance = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return instance;
    }


    private static final String INSERT_ACCOUNT_SQL =
        "INSERT INTO accounts VALUES (0, DEFAULT, ?, ?, ?)";
    private static final String INSERT_PROFILE_SQL =
        "INSERT INTO profiles VALUES (?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
    private static final String SELECT_USER_SQL =
        "SELECT * FROM profiles P JOIN accounts A ON P.account_id = A.id WHERE id = ?";


    public void create(User user) throws SQLException {
        try (Connection connection = DataSource.getConnection();) {
            Long generatedId;
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, user.getAccount().getRoleId());
                preparedStatement.setString(2, user.getAccount().getUsername());
                preparedStatement.setString(3, user.getAccount().getPassword());
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new RuntimeException();
                }
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();
                    generatedId = resultSet.getLong(1);
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE_SQL)) {
                preparedStatement.setLong(1, generatedId);
                preparedStatement.setString(2, user.getProfile().getName());
                preparedStatement.setString(3, user.getProfile().getPhoneNumber());
                preparedStatement.setString(4, user.getProfile().getEmail());
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new RuntimeException();
                }
            }
            connection.commit();
        }
    }

    public User read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return User.of(resultSet);
            }
        }
    }
}
