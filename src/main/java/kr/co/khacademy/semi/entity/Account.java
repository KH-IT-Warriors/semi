package kr.co.khacademy.semi.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Account {

    private Long id;

    private String username;

    private Long statusId;

    private Long roleId;
}
