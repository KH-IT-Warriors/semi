package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.DataSource;
import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.dto.AdminList;
import kr.co.khacademy.semi.exception.join.JoinFailedException;
import kr.co.khacademy.semi.model.Profile;
import kr.co.khacademy.semi.exception.profile.sub.ProfileIdNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ProfileRepository {
    private static final ProfileRepository instance = new ProfileRepository();
    private static final DataSource dataSource = MySqlDataSource.getInstance();

    private ProfileRepository() {
    }

    public static ProfileRepository getInstance() {
        return instance;
    }

    public Profile save(Profile profile) throws SQLException {
        if (profile.getRegisteredTime() == null) {
            insert(profile);
            return Profile.builder().build(); // TODO: return type...
        } else {
            return update(profile);
        }
    }

    private Boolean insert(Profile profile) throws SQLException {
        String sql = "INSERT INTO profile_test (account_id, name, phone_number, email) VALUES(?, ?, ?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setLong(1, profile.getAccountId());
            preparedStatement.setString(2, profile.getName());
            preparedStatement.setString(3, profile.getPhoneNumber());
            preparedStatement.setString(4, profile.getEmail());
            if (preparedStatement.executeUpdate() == 0) {
                throw new JoinFailedException();
            }
            return Boolean.TRUE;
        }
    }

    public Profile updateAdmin(Profile profile) throws SQLException {
        String sql = "UPDATE PROFILES_TEST SET NAME = ?, PHONE_NUMBER = ?, EMAIL = ?, BONUS_POINT = ?, USER_GRADE_ID = ? WHERE ACCOUNT_ID = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getPhoneNumber());
            preparedStatement.setString(3, profile.getEmail());
            preparedStatement.setLong(4, profile.getBonusPoint());
            preparedStatement.setLong(5, profile.getUserGradeId());
            preparedStatement.setLong(6, profile.getAccountId());
            preparedStatement.executeUpdate();
            return profile;
        }
    }

    private Profile update(Profile profile) throws SQLException {
        String sql = "UPDATE PROFILES_TEST SET NAME = ?, PHONE_NUMBER = ?, EMAIL = ?, BONUS_POINT = ?, USER_GRADE_ID = ? WHERE ACCOUNT_ID = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, profile.getName());
            preparedStatement.setString(2, profile.getPhoneNumber());
            preparedStatement.setString(3, profile.getEmail());
            preparedStatement.setLong(4, profile.getBonusPoint());
            preparedStatement.setLong(5, profile.getUserGradeId());
            preparedStatement.setLong(6, profile.getAccountId());
            preparedStatement.executeUpdate();
            return profile;
        }
    }

    public void delete(Long accountId) throws SQLException {
        String sql = "DELETE FROM profiles_test WHERE account_id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, accountId);
            if (preparedStatement.executeUpdate() == 0) {
                throw new ProfileIdNotFoundException();
            }
        }
    }

    public List<Profile> findAll() throws SQLException {
        String sql = "SELECT * FROM profiles_test";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();){
            List<Profile> profiles = new ArrayList<>();
            while (resultSet.next()) {
                Profile profile = mapRow(resultSet);
                profiles.add(profile);
            }
            return Collections.unmodifiableList(profiles);
        }
    }

    public Profile findProfileById(Long accountId) throws SQLException {
        String sql = "SELECT * FROM profiles_test WHERE account_id = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, accountId);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                throw new ProfileIdNotFoundException();
            }
        }
    }

    public List<AdminList> findAllAdminUsername() throws SQLException {
        String sql = "SELECT P.name, A.user_name, R.role_name " +
            "FROM profiles_test P " +
            "JOIN accounts_test A ON P.account_id = A.id " +
            "JOIN roles_test R ON A.role_id = R.id " +
            "WHERE role_id != 1";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();){
            List<AdminList> adminList = new ArrayList<>();
            while(resultSet.next()){
                String username = resultSet.getString("user_name");
                String name = resultSet.getString("name");
                String roleName = resultSet.getString("role_name");
                adminList.add(AdminList.of(username, name, roleName));
            }
            return adminList;
        }
    }

    private Profile mapRow(ResultSet resultSet) throws SQLException {
        return Profile.builder()
            .accountId(resultSet.getLong("account_id"))
            .name(resultSet.getString("name"))
            .phoneNumber(resultSet.getString("phone_number"))
            .email(resultSet.getString("email"))
            .registeredTime(resultSet.getTimestamp("registered_time"))
            .recentConnection(resultSet.getTimestamp("recent_connection"))
            .bonusPoint(resultSet.getLong("bonus_point"))
            .userGradeId(resultSet.getLong("user_grade_id"))
            .build();
    }
}
