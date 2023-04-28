package kr.co.khacademy.semi.model;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Announcement {
    Long id;
    Long accountId;
    String title;
    String contents;
    
    public static Announcement of(HttpServletRequest req) {
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        
        if(!validateTitle(title)) {
            throw new IllegalArgumentException();
        }
        return Announcement.builder()
                .title(req.getParameter("title"))
                .contents(req.getParameter("contents"))
                .build();
    }
    
    
    private static boolean validateTitle(String title) {
        return (6 <= title.length()) && (title.length() <= 100);
    }


    public static Announcement of(ResultSet resultSet) {
        try {
        return Announcement.builder()
                .id(resultSet.getLong("id"))
                .accountId(resultSet.getLong("account_id"))
                .title(resultSet.getString("title"))
                .contents(resultSet.getString("contents"))
                .build();
        }catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
    
    

}
