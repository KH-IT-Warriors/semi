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
public class Faq {

    Long id;
    Long accountId;
    String title;
    String contents;

    public static Faq of(HttpServletRequest req) {

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        if(!validateTitle(title)) {
            throw new IllegalArgumentException();
        }
        return Faq.builder()
                .title(req.getParameter("title"))
                .contents(req.getParameter("contents"))
                .build();
    }

    private static Boolean validateTitle(String title) {
        return (6 <= title.length()) && (title.length() <= 100);
    }

    public static Faq of(ResultSet resultSet) {
        try {
            return Faq.builder()
                    .id(resultSet.getLong("id"))
                    .accountId(resultSet.getLong("account_id"))
                    .title(resultSet.getString("title"))
                    .contents(resultSet.getString("contents"))
                    .build();
        }catch (SQLException e) {
            throw new IllegalArgumentException();
        }

    }


}
