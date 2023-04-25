package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.entity.Password;
import kr.co.khacademy.semi.exception.login.sub.AccountIdNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordRepository {

    private static final PasswordRepository instance = new PasswordRepository();
    private static final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();

    private PasswordRepository() {
    }

    public static PasswordRepository getInstance() {
        return instance;
    }

    public Password findByAccountId(Long accountId) throws SQLException {
        String sql = "SELECT * FROM PASSWORDS_TEST WHERE ACCOUNT_ID = ?";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, accountId);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if (resultSet.next()) {
                    String password = resultSet.getString("PASSWORD");
                    return Password.of(accountId, password);
                } else {
                    throw new AccountIdNotFoundException();
                }
            }

        }
        /*
         * TODO:
         *  1. accountId 를 사용하여 Password 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Password 객체를 반환하세요.
         *  3. 조회에 실패하면 AccountIdNotFoundException 예외를 발생 시키세요.
         */
    }

    public void insertNewPassword(Long createdAccountId, String encryptedPassword) throws SQLException {
        String sql = "INSERT INTO PASSWORD_TEST VALUES(?, ?)";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, createdAccountId);
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.executeUpdate();
        }
    }
}
