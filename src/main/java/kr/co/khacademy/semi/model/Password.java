package kr.co.khacademy.semi.model;

import kr.co.khacademy.semi.common.encryption.BasicPasswordEncryptor;
import kr.co.khacademy.semi.common.encryption.PasswordEncryptionProvider;
import kr.co.khacademy.semi.exception.join.sub.InvalidPasswordException;
import lombok.Builder;
import lombok.Value;

@Value(staticConstructor = "of")
@Builder
public class Password {
    private static final PasswordEncryptionProvider basicPasswordEncryptor = BasicPasswordEncryptor.getInstance();


    Long accountId;

    String encryptedPassword;


    private Password(Long accountId, String plainPassword) {
        if ((plainPassword != null) && !validatePassword(plainPassword)) {
            throw new InvalidPasswordException();
        }
        String encryptedPassword = basicPasswordEncryptor.encryptPassword(plainPassword);
        this.accountId = accountId;
        this.encryptedPassword = encryptedPassword;
    }

    public static Password of(Long accountId, String plainPassword){
        return new Password(accountId, plainPassword);
    }

    private Boolean validatePassword(String plainPassword) {
        if (!hasUpperCase(plainPassword)) {
            return Boolean.FALSE;
        }
        if (!hasDownCase(plainPassword)) {
            return Boolean.FALSE;
        }
        if (hasNumber(plainPassword)) {
            return Boolean.FALSE;
        }
        if (hasSpecialWord(plainPassword)) {
            return Boolean.FALSE;
        }
        if (between(plainPassword, 8, 15)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean hasUpperCase(String data) {
        String hasUpperCase = ".*[A-Z].*?$";
        return data.matches(hasUpperCase);
    }

    public Boolean hasDownCase(String data) {
        String hasDownCase = ".*[a-z].*?$";
        return data.matches(hasDownCase);
    }

    public Boolean hasNumber(String data) {
        String hasNumber = ".*[0-9].*?$";
        return data.matches(hasNumber);
    }

    public Boolean hasSpecialWord(String data) {
        String hasSpecialWord = ".*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?].*?$";
        return data.matches(hasSpecialWord);
    }

    public Boolean between(String data, int start, int end) {
        String between = ".{"+start+","+end+"}";
        return data.matches(between);
    }
}
