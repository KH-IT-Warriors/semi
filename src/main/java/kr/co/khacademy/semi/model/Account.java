package kr.co.khacademy.semi.model;

import kr.co.khacademy.semi.exception.join.sub.InvalidUsernameException;
import lombok.Builder;
import lombok.Value;

import java.util.Optional;

@Value
@Builder
public class Account {

    Long id;

    String username;

    Long statusId;

    Long roleId;

    private Account(Long id, String username, Long statusId, Long roleId) {
        if ((username != null) && !validateUsername(username)) {
            throw new InvalidUsernameException();
        }
        this.id = id;
        this.username = username;
        this.statusId = statusId;
        this.roleId = roleId;
    }

    private Boolean validateUsername(String username) {
        if (!hasAlphabet(username)) {
            return Boolean.FALSE;
        }
        if (!hasNumber(username)) {
            return Boolean.FALSE;
        }
        if (!between(username, 6, 10)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private Boolean hasAlphabet(String data) {
        String hasAlphabet = ".*[a-zA-Z].*?$";
        return data.matches(hasAlphabet);
    }

    private Boolean hasNumber(String data) {
        String hasNumber = ".*[0-9].*?$";
        return data.matches(hasNumber);
    }

    private Boolean between(String data, int start, int end) {
        String between = ".{"+start+","+end+"}";
        return data.matches(between);
    }
}
