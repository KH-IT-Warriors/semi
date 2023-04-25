package kr.co.khacademy.semi.common.encryption;

public interface PasswordEncryptionProvider {

    boolean checkPassword(String plainPassword, String encryptedPassword);
}
