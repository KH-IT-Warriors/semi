package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.sql.ResultSet;
import java.sql.SQLException;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Grade {
    Long id;
    String name;
    Double bonusRate;
    Long condition;

    public static Grade of(Long id, String name, Double bonusRate, Long condition) {
        return Grade.builder()
            .id(id)
            .name(name)
            .build();
    }

    public static Grade of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        String name = resultSet.getString(2);
        Double bonusRate = resultSet.getDouble(3);
        Long condition = resultSet.getLong(4);
        return Grade.of(id, name, bonusRate, condition);
    }
}
