package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Role {

    Long id;
    String name;

    public static Role of(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("role-id"));
        String name = req.getParameter("role-name");
        return Role.builder()
            .id(id)
            .name(name)
            .build();
    }

    public static Role of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        return Role.builder()
            .id(id)
            .name(name)
            .build();
    }
}
