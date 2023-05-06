package kr.co.khacademy.semi.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
    Timestamp date;
    
    public static Announcement of(HttpServletRequest req) {
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Long id = Long.valueOf(req.getParameter("id"));
        
        if(!validateTitle(title)) {
            throw new IllegalArgumentException();
        }
        return Announcement.builder()
                .id(id)
                .title(title)
                .contents(contents)
                .build();
    }
        
    private static boolean validateTitle(String title) {
        return (6 <= title.length()) && (title.length() <= 100);
    }

    public static Announcement of(ResultSet resultSet) throws SQLException {
        return Announcement.builder()
                .id(resultSet.getLong("id"))
                .accountId(resultSet.getLong("account_id"))
                .title(resultSet.getString("title"))
                .contents(resultSet.getString("contents"))
                .date(resultSet.getTimestamp("announcement_date"))
                .build();
    }
}
