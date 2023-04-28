package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Account;

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
}
