package domains.account.commons;

import domains.exceptions.InvalidPasswordException;
import lombok.NonNull;
import lombok.Value;
import lombok.With;

@Value(staticConstructor = "of")
public class Password {
    @NonNull
    long accountId;
    @NonNull
    @With
    String password;

    private Password(long accountId, String password) {
        if (!validatesPassword(password)) {
            InvalidPasswordException invalidPasswordException = new InvalidPasswordException("잘못된 형식의 비밀번호입니다.");
            invalidPasswordException.printStackTrace();
            throw invalidPasswordException;
        }
        this.accountId = accountId;
        this.password = password;
    }

    private boolean validatesPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?]).{8,16}$");
    }
}
