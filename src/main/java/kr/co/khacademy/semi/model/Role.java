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

    public static Role of(Long id, String name) {
        return Role.builder()
            .id(id)
            .name(name)
            .build();
    }

    public static Role of(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("role-id"));
        String name = req.getParameter("role-name");
        return Role.of(id, name);
    }

    public static Role of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("R.id");
        String name = resultSet.getString("R.name");
        return Role.builder()
            .id(id)
            .name(name)
            .build();
    }
}
