package kr.co.khacademy.semi.common;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordEncryptor {

    private static final BasicPasswordEncryptor basicPasswordEncryptor = new BasicPasswordEncryptor();

    private PasswordEncryptor() {
    }

    public static BasicPasswordEncryptor get() {
        return basicPasswordEncryptor;
    }
}
