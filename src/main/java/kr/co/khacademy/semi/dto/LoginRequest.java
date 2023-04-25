package kr.co.khacademy.semi.dto;

import lombok.*;

@Value(staticConstructor = "of")
public class LoginRequest {

    String username;

    String plainPassword;
}
