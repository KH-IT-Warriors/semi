package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.entity.Profile;

import java.sql.*;


public class ProfileRepository {
    private static final ProfileRepository instance = new ProfileRepository();
    private static final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();

    private ProfileRepository() {
    }

    public static ProfileRepository getInstance() {
        return instance;
    }

    public Profile save(Profile profile) throws SQLException {
        if (profile.getRegisteredTime() == null) {
            String sql = "INSERT INTO PROFILES_TEST VALUES(?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT ,DEFAULT)";
            String[] columnNames = new String[]{"REGISTERED_TIME", "RECENT_CONNECTION"};
            try(Connection connection = mySqlDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);) {
                preparedStatement.setLong(1, profile.getAccountId());
                preparedStatement.setString(2, profile.getName());
                preparedStatement.setString(3, profile.getPhoneNumber());
                preparedStatement.setString(4, profile.getEmail());
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    resultSet.next();
                    Timestamp registeredTime = resultSet.getTimestamp(1);
                    Timestamp recentConnection = resultSet.getTimestamp(2);
                    return Profile.builder()
                        .accountId(profile.getAccountId())
                        .name(profile.getName())
                        .phoneNumber(profile.getPhoneNumber())
                        .email(profile.getEmail())
                        .registeredTime(registeredTime)
                        .recentConnection(recentConnection)
                        .bonusPoint(0L)
                        .userGradeId(1L)
                        .build();
                }
            }
        } else {
            String sql = "UPDATE PROFILES_TEST SET NAME = ?, PHONE_NUMBER = ?, EMAIL = ?, BONUS_POINT = ?, USER_GRADE_ID = ? WHERE ACCOUNT_ID = ?";
            String[] columnNames = new String[]{"REGISTERED_TIME", "RECENT_CONNECTION"};
            try(Connection connection = mySqlDataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql, columnNames);) {
                preparedStatement.setString(1, profile.getName());
                preparedStatement.setString(2, profile.getPhoneNumber());
                preparedStatement.setString(3, profile.getEmail());
                preparedStatement.setLong(4, profile.getBonusPoint());
                preparedStatement.setLong(5, profile.getUserGradeId());
                preparedStatement.setLong(6, profile.getAccountId());
                try(ResultSet resultSet = preparedStatement.executeQuery();){
                    resultSet.next();
                    Timestamp registeredTime = resultSet.getTimestamp("REGISTERED_TIME");
                    Timestamp recentConnection = resultSet.getTimestamp("RECENT_CONNECTION");
                    return Profile.builder()
                        .accountId(profile.getAccountId())
                        .name(profile.getName())
                        .phoneNumber(profile.getPhoneNumber())
                        .email(profile.getEmail())
                        .registeredTime(registeredTime)
                        .recentConnection(recentConnection)
                        .bonusPoint(profile.getBonusPoint())
                        .userGradeId(profile.getUserGradeId())
                        .build();
                }

            }
        }
    }

    public Profile findUserInformationById(Long accountId) throws SQLException {
        String sql = "SELECT * FROM PROFILES_TEST";
        try (Connection connection = mySqlDataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery();) {
            resultSet.next();
            String name = resultSet.getString("NAME");
            String phoneNumber = resultSet.getString("PHONE_NUMBER");
            String email = resultSet.getString("EMAIL");
            Timestamp registeredTime = resultSet.getTimestamp("REGISTERED_DATE");
            Timestamp recentConnection = resultSet.getTimestamp("RECENT_CONNECTION");
            Long userGradeId = resultSet.getLong("USER_GRADE_ID");
            return Profile.builder()
                .accountId(accountId)
                .name(name)
                .phoneNumber(phoneNumber)
                .email(email)
                .registeredTime(registeredTime)
                .recentConnection(recentConnection)
                .userGradeId(userGradeId)
                .build();
        }
    }
}
