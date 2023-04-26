package kr.co.khacademy.semi.dto;

import lombok.Data;

@Data
public class FindPasswordRequest {
    String username;
    String name;
    String phoneNumber;

    private FindPasswordRequest(String username, String name, String phoneNumber){
        this.username = username;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static FindPasswordRequest of(String username, String name, String phoneNumber) {
        return new FindPasswordRequest(username, name, phoneNumber);
    }
}
