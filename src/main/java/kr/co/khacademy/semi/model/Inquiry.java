package kr.co.khacademy.semi.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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
    
    public static Inquiry of(HttpServletRequest req) {
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        
        if (!validateTitle(title)) {
            throw new IllegalArgumentException();
        }
        return Inquiry.builder()
                .title(title)
                .contents(contents)
                .build();
    }
    
    private static boolean validateTitle(String title) {
        return (6 <= title.length()) && (title.length() <= 100);
    }

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
