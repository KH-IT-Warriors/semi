package kr.co.khacademy.semi.model;

import lombok.Builder;
import lombok.Value;

import java.sql.ResultSet;
import java.sql.SQLException;

@Value(staticConstructor = "of")
@Builder
public class Account {

    Long id;
    Long statusId;
    Long roleId;
    String username;

    public static Account of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long statusId = resultSet.getLong("status_id");
        Long roleId = resultSet.getLong("role_id");
        String username = resultSet.getString("username");
        return Account.builder()
            .id(id)
            .statusId(statusId)
            .roleId(roleId)
            .username(username)
            .build();
    }
}
