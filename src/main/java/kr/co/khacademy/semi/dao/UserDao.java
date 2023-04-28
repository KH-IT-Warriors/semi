package kr.co.khacademy.semi.dao;

import com.mysql.cj.protocol.Resultset;
import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDao {

    private static final UserDao instance = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    private static final String SELECT_USER_SQL =
        "SELECT " +
            "A.id, A.username, P.name, P.phone_number, P.email, R.role_name, G.grade_name, P.mileage, P.created " +
            "FROM profiles P " +
            "JOIN accounts A ON P.account_id = A.id " +
            "JOIN roles R ON A.role_id = R.id " +
            "JOIN grades G ON P.grade_id = G.id " +
            "WHERE account_id = ?";

    public Optional<User> read(Long id){
        try (Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return Optional.of(User.of(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }
}
