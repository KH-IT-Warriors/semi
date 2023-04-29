package kr.co.khacademy.semi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Inquiry {
    Long id;
    Long accountId;
    String title;
    String contents;
    
    public static Inquiry of(ResultSet resultSet) {
        try {
        return Inquiry.builder()
                .id(resultSet.getLong("id"))
                .accountId(resultSet.getLong("account_id"))
                .title(resultSet.getString("title"))
                .contents(resultSet.getString("contents"))
                .build();
        }catch(SQLException e) {
            throw new IllegalArgumentException();
        }
        
    }
    
}
