package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.model.Password;
import kr.co.khacademy.semi.exception.login.sub.AccountIdNotFoundException;
import kr.co.khacademy.semi.model.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordRepository {

    private static final PasswordRepository instance = new PasswordRepository();
    private static final MySqlDataSource dataSource = MySqlDataSource.getInstance();

    private PasswordRepository() {
    }

    public static PasswordRepository getInstance() {
        return instance;
    }

    public Password findByAccountId(Long accountId) throws SQLException {
        String sql = "SELECT * FROM passwords_test WHERE account_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, accountId);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    String password = resultSet.getString("password");
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

    public Password save(Password password, boolean flag) throws SQLException {
        if (flag) {
            String sql = "INSERT INTO passwords_test VALUES(?, ?)";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setLong(1, password.getAccountId());
                preparedStatement.setString(2, password.getEncryptedPassword());
                preparedStatement.executeUpdate();
                return Password.of(password.getAccountId(), password.getEncryptedPassword());
            }
        } else {
            String sql = "UPDATE passwords_test SET password = ? WHERE account_id = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
                preparedStatement.setString(1, password.getEncryptedPassword());
                preparedStatement.setLong(2, password.getAccountId());
                preparedStatement.executeUpdate();
                return Password.of(password.getAccountId(), password.getEncryptedPassword());
            }
        }
    }

    private Password insert(Password password) throws SQLException {
        String sql = "INSERT INTO passwords_test VALUES(?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, password.getAccountId());
            preparedStatement.setString(2, password.getEncryptedPassword());
            preparedStatement.executeUpdate();
            return Password.of(password.getAccountId(), password.getEncryptedPassword());
        }
    } // TODO: insert, update

    public Password findByPhoneNumber(Account account, Profile profile) throws SQLException {
        String sql = "SELECT PW.* FROM passwords_test PW JOIN user_accounts_test A ON PW.account_id = A.id JOIN user_profiles_test P ON A.id = P.account_id WHERE user_name = ? AND name = ? AND phone_number = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, profile.getName());
            preparedStatement.setString(3, profile.getPhoneNumber());
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                resultSet.next();
                Long accountId = resultSet.getLong("account_id");
                String encryptedPassword = resultSet.getString("password");
                return Password.of(accountId, encryptedPassword);
            }
        }
    }
}
