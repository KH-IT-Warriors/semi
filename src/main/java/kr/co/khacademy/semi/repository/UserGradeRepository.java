package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;
import kr.co.khacademy.semi.model.UserGrade;
import kr.co.khacademy.semi.exception.usergrade.sub.UserGradeNameNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGradeRepository {
    private static final UserGradeRepository instance = new UserGradeRepository();
    private static final MySqlDataSource dataSource = MySqlDataSource.getInstance();
    private UserGradeRepository(){
    }
    public static UserGradeRepository getInstance() {
        return instance;
    }
    public UserGrade findByName(String grade) throws SQLException {
        String sql = "SELECT * FROM user_grade_test WHERE grade_name = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, grade);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                if (resultSet.next()) {
                    return mapRow(resultSet);
                }
                throw new UserGradeNameNotFoundException();
            }
        }
    }

    private UserGrade mapRow(ResultSet resultSet) throws SQLException {
        return UserGrade.builder()
            .id(resultSet.getLong("id"))
            .gradeName(resultSet.getString("grade_name"))
            .accumulateRate(resultSet.getDouble("accumulate_rate"))
            .achievementCondition(resultSet.getLong("achievement_condition"))
            .build();
    }
}
