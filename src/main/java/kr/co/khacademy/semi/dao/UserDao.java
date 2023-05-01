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

    private static final String SELECT_ALL_USER_SQL =
        "SELECT * FROM profiles P JOIN accounts A ON P.account_id = A.id";
    private static final String SELECT_USER_SQL =
        "SELECT * FROM profiles P JOIN accounts A ON P.account_id = A.id WHERE id = ?";


    public List<User> read() throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_SQL);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                users.add(User.of(resultSet));
            }
            return users;
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