package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {

    private static final AccountDao instance = new AccountDao();
    private AccountDao() {
    }
    public static AccountDao getInstance() {
        return instance;
    }

    public Account read(Long id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try(Connection connection = DataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                return Account.of(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}
