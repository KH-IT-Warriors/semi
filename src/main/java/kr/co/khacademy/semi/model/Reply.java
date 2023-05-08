package kr.co.khacademy.semi.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Reply {

    Long id;
    Long accountId;
    Long productId;
    String contents;
    Timestamp created;
    Timestamp modified;

    public static Reply of(HttpServletRequest req) {
        //TODO: 세션아디 받아오기
        String contents = Optional.of(req.getParameter("contents"))
            .filter(Reply::validateContents)
            .orElseThrow(RuntimeException::new);

        Long productId = Optional.of(req.getParameter("product-id"))
            .map(Long::valueOf)
            .orElseThrow(RuntimeException::new);

        return Reply.builder()
            .productId(productId)
            .contents(contents)
            .build();
    }
    public static Reply of(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(1);
        Long accountId = resultSet.getLong(2);
        Long productId = resultSet.getLong(3);
        String contents = resultSet.getString(4);
        Timestamp created = resultSet.getTimestamp(5);
        Timestamp modified = resultSet.getTimestamp(6);

        return Reply.builder()
            .id(id)
            .accountId(accountId)
            .productId(productId)
            .contents(contents)
            .created(created)
            .modified(modified)
            .build();
    }

    public static Boolean validateContents(String contents){
        return contents.length() <= 500;
    }



    public String getRelativeCreatedData(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime createdData = created.toLocalDateTime();
        Duration diffTime = Duration.between(createdData,now);

        if (diffTime.toMinutes() < 1L) {
            return String.format("%d초 전", diffTime.toSeconds());
        } else if (diffTime.toMinutes() < 60L) {
            String.format("%d분 전", diffTime.toMinutes());
        } else if (diffTime.toHours() < 12L) {
            String.format("%d시간 전", diffTime.toHours());
        } else if (diffTime.toDays() < 1L) {
            return String.format("%d일 이내", diffTime.toDays());
        }

        return createdData.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getRelativeModifiedData(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime modifiedData = modified.toLocalDateTime();
        Duration diffTime = Duration.between(modifiedData, now);

        if (diffTime.toMinutes() < 1L) {
            return String.format("%d초 전(수정 됨)", diffTime.toSeconds());
        } else if (diffTime.toMinutes() < 60L) {
          return   String.format("%d분 전 (수정 됨)", diffTime.toMinutes());
        } else if (diffTime.toHours() < 12L) {
          return   String.format("%d시간 전 (수정 됨)", diffTime.toHours());
        } else if (diffTime.toDays() < 1L) {
            return String.format("%d일 이내 (수정 됨)", diffTime.toDays());
        }
        return modifiedData.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }


}