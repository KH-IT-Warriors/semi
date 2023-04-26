package kr.co.khacademy.semi.dto;

import lombok.Data;

@Data
public class FindUsernameRequest {
    String name;
    String phoneNumber;

    private FindUsernameRequest(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static FindUsernameRequest of(String name, String phoneNumber) {
        return new FindUsernameRequest(name, phoneNumber);
    }
}
