package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class AccountDao {

    private static final AccountDao instance = new AccountDao();

    private AccountDao() {
    }
    public static AccountDao getInstance() {
        return instance;
    }

    private static final String SELECT_ACCOUNT_SQL = "SELECT * FROM accounts WHERE id = ?";
    private static final String LOGIN_SQL = "SELECT * FROM accounts WHERE id = ? AND password = ?";

    public Optional<Account> read(Long id) {
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ACCOUNT_SQL);) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                return Optional.of(Account.of(resultSet));
            }
        } catch (SQLException e) {
            return Optional.empty();
        }
    }

    public boolean read(Login login) {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(LOGIN_SQL)) {
            preparedStatement.setLong(1, login.getId());
            preparedStatement.setString(2, login.getPassword());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
