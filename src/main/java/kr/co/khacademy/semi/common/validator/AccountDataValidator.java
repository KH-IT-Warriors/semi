package kr.co.khacademy.semi.common.validator;

public class AccountDataValidator implements Validator {

    private static final AccountDataValidator instance = new AccountDataValidator();

    private AccountDataValidator() {
    }

    public static AccountDataValidator getInstance() {
        return instance;
    }

    @Override
    public boolean hasAlphabet(String data) {
        String hasAlphabet = ".*[a-zA-Z].*?$";
        return data.matches(hasAlphabet);
    }

    @Override
    public boolean hasUpperCase(String data) {
        String hasUpperCase = ".*[A-Z].*?$";
        return data.matches(hasUpperCase);
    }

    @Override
    public boolean hasDownCase(String data) {
        String hasDownCase = ".*[a-z].*?$";
        return data.matches(hasDownCase);
    }

    @Override
    public boolean hasNumber(String data) {
        String hasNumber = ".*[0-9].*?$";
        return data.matches(hasNumber);
    }

    @Override
    public boolean hasSpecialWord(String data) {
        String hasSpecialWord = ".*[!@#$%^&*()\\-=_+\\[\\]{};':\\\"\\\\|,.<>/?].*?$";
        return data.matches(hasSpecialWord);
    }

    @Override
    public boolean between(String data, int start, int end) {
        String between = ".{"+start+","+end+"}";
        return data.matches(between);
    }

    @Override
    public boolean validatesUsername(String data) {
        if (!hasAlphabet(data)) {
            return false;
        }
        if (!hasNumber(data)) {
            return false;
        }
        if (!between(data, 6, 10)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validatesPassword(String data) {
        if (!hasDownCase(data)) {
            return false;
        }
        if (!hasUpperCase(data)) {
            return false;
        }
        if (!hasNumber(data)) {
            return false;
        }
        if (!hasSpecialWord(data)) {
            return false;
        }
        if (!between(data, 8, 15)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean validatesPhoneNumber(String data) {
        String phoneNumberForm = "^01\\d\\d{8}$";
        return data.matches(phoneNumberForm);
    }

    @Override
    public boolean validatesEmail(String data) {
        String emailForm = "^[a-z0-9]+@.+(\\.com)$";
        return data.matches(emailForm);
    }
}
