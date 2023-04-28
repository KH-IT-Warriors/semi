package kr.co.khacademy.semi.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Reply {

    Long id;
    Long parentReplyId;
    Long accountId;
    Long productId;
    String contents;
    Timestamp created;

    public static Reply of(HttpServletRequest req) {
        //TODO: 세션아디 받아오기
        String contents = Optional.of(req.getParameter("contents"))
            .filter(Reply::validateContents)
            .orElseThrow(RuntimeException::new);

        Long parentReplyId = Optional.ofNullable(req.getParameter("parent-reply-id"))
            .map(Long::valueOf)
            .orElse(0L);

        return Reply.builder()
            .contents(contents)
            .parentReplyId(parentReplyId)
            .build();
    }
    public static Reply of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        Long parentReplyId = resultSet.getLong(2);
        Long accountId = resultSet.getLong(3);
        Long productId = resultSet.getLong(4);
        String contents = resultSet.getString(5);
        Timestamp created = resultSet.getTimestamp(6);

        return Reply.builder()
            .id(id)
            .parentReplyId(parentReplyId)
            .accountId(accountId)
            .productId(productId)
            .contents(contents)
            .created(created)
            .build();
    }

    public static Boolean validateContents(String contents){
        return contents.length() <= 500;
    }
}
