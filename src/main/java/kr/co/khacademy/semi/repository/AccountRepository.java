package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.DataSource;
import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.model.Account;
import kr.co.khacademy.semi.exception.account.sub.PhoneNumberNotFoundException;
import kr.co.khacademy.semi.exception.login.sub.AccountIdNotFoundException;
import kr.co.khacademy.semi.exception.login.sub.UsernameNotFoundException;
import kr.co.khacademy.semi.model.Profile;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {

    private static final AccountRepository instance = new AccountRepository();
    private static final DataSource dataSource = MySqlDataSource.getInstance();

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        return instance;
    }

    public Account findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM user_account_test WHERE USER_NAME = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
            }
        }
        throw new UsernameNotFoundException("아이디를 찾을 수 없습니다.");
        /*
         * TODO:
         *  1. username 을 사용하여 Account 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Account 객체를 반환하세요.
         *  3. 조회에 실패하면 UsernameMissMatchException 예외를 발생 시키세요.
         */
    }

    public Account save(Account account) throws SQLException {
        String sql = "INSERT INTO user_accounts_test (user_name) VALUES(?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys();) {
                resultSet.next();  // insert 후 select // procedure mysql에서 함수 CallableStatement
                Long id = resultSet.getLong(1);
                Long statusId = 1L;
                Long roleId = 1L;
                String username = account.getUsername();
                return Account.builder()
                    .id(id)
                    .statusId(statusId)
                    .roleId(roleId)
                    .username(username)
                    .build();
            }
        } // TODO: INSERT 문 리턴 값 받아오는 법 찾기
    }

    public void delete(Long accountId) throws SQLException {
        String sql = "DELETE FROM user_accounts_test WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, accountId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new AccountIdNotFoundException();
            }
        }
    }

    public Account findByPhoneNumber(Profile profile) throws SQLException {
        String sql = "SELECT A.* FROM user_accounts_test A JOIN user_profiles_test P ON A.id = P.account_id WHERE name = ? AND phone_number = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getPhoneNumber());
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                throw new PhoneNumberNotFoundException();
            }
        }
    }

    public Account findById(Long accountId) throws SQLException {
        String sql = "SELECT * FROM user_accounts_test WHERE id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, accountId);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                throw new AccountIdNotFoundException();
            }
        }
    }

    private Account mapRow(ResultSet resultSet) throws SQLException {
        return Account.builder()
            .id(resultSet.getLong("id"))
            .statusId(resultSet.getLong("status_id"))
            .roleId(resultSet.getLong("role_id"))
            .username(resultSet.getString("user_name"))
            .build();
    }
}
