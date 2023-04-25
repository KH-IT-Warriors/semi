package kr.co.khacademy.semi.entity;

import lombok.Value;

@Value(staticConstructor = "of")
public class Password {

    private Long accountId;

    private String encryptedPassword;

}
