package kr.co.khacademy.semi.repository;

import kr.co.khacademy.semi.conf.MySqlDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserGradeRepository {
    private static final UserGradeRepository instance = new UserGradeRepository();
    private static final MySqlDataSource mySqlDataSource = MySqlDataSource.getInstance();
    private UserGradeRepository(){
    }
    public static UserGradeRepository getInstance() {
        return instance;
    }
    public Long findUserGradeIdByName(String grade) throws SQLException {
        String sql = "SELECT ID FROM USER_GRADES_TEST WHERE GRADE_NAME = ?";
        try(Connection connection = mySqlDataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1, grade);
            try(ResultSet resultSet = preparedStatement.executeQuery();){
                resultSet.next();
                return resultSet.getLong(1);
            }
        }
    }
}
