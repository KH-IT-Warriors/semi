package kr.co.khacademy.semi.model;

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
        String content = req.getParameter("contents");
        
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
    
    
}
