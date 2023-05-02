package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Grade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GradeDao {

    private static final GradeDao instance = new GradeDao();

    private GradeDao() {
    }

    public static GradeDao getInstance() {
        return instance;
    }

    private static final String SELECT_GRADE_NAME_SQL =
        "SELECT * FROM grades WHERE id = ?";

    public Grade read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_GRADE_NAME_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return Grade.of(resultSet);
                } else {
                    throw new SQLException();
                }
            }
        }
    }
}
