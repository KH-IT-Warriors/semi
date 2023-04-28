package kr.co.khacademy.semi.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class AdminList {
    String username;
    String name;
    String roleName;
}
