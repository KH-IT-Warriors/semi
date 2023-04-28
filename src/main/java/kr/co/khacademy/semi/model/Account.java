package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Account {

    Long id;
    Long statusId;
    Long roleId;
    String username;

    public static Account of(HttpServletRequest req) {
        String username = req.getParameter("username");

        Optional.ofNullable(username)
            .filter(Account::validateUsername)
            .orElseThrow(RuntimeException::new);
        return Account.builder()
            .username(username)
            .build();
    }

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

    private static boolean validateUsername(String username) {
        String hasAlphabet = ".*[a-zA-Z].*?$";
        String hasNumber = ".*[0-9].*?$";
        if (!username.matches(hasAlphabet)) {
            return false;
        }
        if (!username.matches(hasNumber)) {
            return false;
        }
        if (6 < username.length() && username.length() < 10) {
            return false;
        }
        return true;
    }
}
