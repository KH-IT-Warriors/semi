package kr.co.khacademy.semi.entity;

import lombok.Data;

@Data
public class Password {

    private Long accountId;

    private String encryptedPassword;
}
