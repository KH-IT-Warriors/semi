package kr.co.khacademy.semi.common.validator;

public interface Validator {
    boolean hasAlphabet(String data);

    boolean hasUpperCase(String data);

    boolean hasDownCase(String data);

    boolean hasNumber(String data);

    boolean hasSpecialWord(String data);

    boolean between(String data, int start, int end);

    boolean validatesUsername(String data);

    boolean validatesPassword(String data);

    boolean validatesPhoneNumber(String data);

    boolean validatesEmail(String data);
}
