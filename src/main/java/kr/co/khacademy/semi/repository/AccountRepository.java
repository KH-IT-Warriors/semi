package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.dto.JoinRequest;
import kr.co.khacademy.semi.entity.Account;
import kr.co.khacademy.semi.exception.login.sub.UsernameNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRepository {

    private static final AccountRepository instance = new AccountRepository();
    private static final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();

    private AccountRepository() {
    }

    public static AccountRepository getInstance() {
        return instance;
    }

    public Account findByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM USER_ACCOUNTS_TEST WHERE USER_NAME = ?";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, username);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if (resultSet.next()) {
                    long id = resultSet.getLong("ID");
                    long statusId = resultSet.getLong("STATUS_ID");
                    long roleId = resultSet.getLong("ROLE_ID");
                    return Account.builder()
                        .id(id)
                        .username(username)
                        .statusId(statusId)
                        .roleId(roleId)
                        .username(username)
                        .build();
                } else {
                    throw new UsernameNotFoundException("아이디를 찾을 수 없습니다.");
                }
            }
        }
        /*
         * TODO:
         *  1. username 을 사용하여 Account 테이블 조회하는 기능을 구현 하세요.
         *  2. 조회에 성공하면 Account 객체를 반환하세요.
         *  3. 조회에 실패하면 UsernameMissMatchException 예외를 발생 시키세요.
         */
    }

    public Account save(JoinRequest joinRequest) throws SQLException {
        String sql = "INSERT INTO USER_ACCOUNTS_TEST VALUES(0, ?, ?, ?)";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, 1);
            preparedStatement.setLong(2, 1);
            preparedStatement.setString(3, joinRequest.getUsername());
            preparedStatement.executeUpdate();
            try(ResultSet resultSet = preparedStatement.getGeneratedKeys();){
                resultSet.next();
                Long id = resultSet.getLong(1);
                Long statusId = 1L;
                Long roleId = 1L;
                String username = joinRequest.getUsername();
                return Account.builder()
                    .id(id)
                    .statusId(statusId)
                    .roleId(roleId)
                    .username(username)
                    .build();
            }
        }
    }

    public void deleteById(Long accountId) throws SQLException {
        String sql = "DELETE FROM USER_ACCOUNTS_TEST WHERE ID = ?";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, accountId);
            preparedStatement.executeUpdate();
            connection.commit();
        }
    }
}
