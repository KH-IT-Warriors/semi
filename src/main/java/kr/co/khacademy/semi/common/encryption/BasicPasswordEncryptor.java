package kr.co.khacademy.semi.common.encryption;

import org.jasypt.util.password.PasswordEncryptor;

public class BasicPasswordEncryptor implements PasswordEncryptionProvider {

    private static final BasicPasswordEncryptor instance = new BasicPasswordEncryptor();

    private static final PasswordEncryptor passwordEncryptor = new org.jasypt.util.password.BasicPasswordEncryptor();

    private BasicPasswordEncryptor() {
    }

    public static BasicPasswordEncryptor getInstance() {
        return instance;
    }

    @Override
    public boolean checkPassword(String plainPassword, String encryptedPassword) {
        return passwordEncryptor.checkPassword(plainPassword, encryptedPassword);
    }

    public String encryptPassword(String plainPassword) {
        return passwordEncryptor.encryptPassword(plainPassword);
    }
}
