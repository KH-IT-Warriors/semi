package kr.co.khacademy.semi.model;

import kr.co.khacademy.semi.common.PasswordEncryptor;
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
    String password;

    public static Account of(HttpServletRequest req) {
        Long id = Long.valueOf(req.getParameter("accountId"));
        Long roleId = Long.valueOf(req.getParameter("roleId"));
        String username = req.getParameter("username");
        String password = req.getParameter(req.getParameter("password"));
        Optional.ofNullable(username)
            .filter(Account::validateUsername)
            .orElseThrow(RuntimeException::new);
        Optional.ofNullable(password)
            .filter(Account::validatePassword)
            .map(Account::encryptPassword);
        return Account.builder()
            .id(id)
            .roleId(roleId)
            .username(username)
            .password(password)
            .build();
    }

    public static Account of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long statusId = resultSet.getLong("status_id");
        Long roleId = resultSet.getLong("role_id");
        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        return Account.builder()
            .id(id)
            .statusId(statusId)
            .roleId(roleId)
            .username(username)
            .password(password)
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

    private static boolean validatePassword(String password) {
        String hasUpperCase = ".*[A-Z].*?$";
        String hasDownCase = ".*[a-z].*?$";
        String hasNumber = ".*[0-9].*?$";
        String hasSpecialWord = ".*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?].*?$";
        if (!password.matches(hasUpperCase)) {
            return false;
        }
        if (!password.matches(hasDownCase)) {
            return false;
        }
        if (!password.matches(hasNumber)) {
            return false;
        }
        if (!password.matches(hasSpecialWord)) {
            return false;
        }
        return true;
    }

    private static String encryptPassword(String plainPassword) {
        return PasswordEncryptor.get().encryptPassword(plainPassword);
    }
}

