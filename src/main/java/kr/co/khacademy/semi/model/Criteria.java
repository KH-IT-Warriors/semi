package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Criteria {

    private static final Long DEFAULT_PAGE_NUMBER = 1L;
    private static final Long DEFAULT_AMOUNT = 10L;
    private static final String DEFAULT_TYPE = "";
    private static final String DEFAULT_KEYWORD = "";

    Long pageNumber;
    Long amount;
    String type;
    String keyword;

    public static Criteria of(HttpServletRequest req) {
        Long pageNumber = Optional.ofNullable(req.getParameter("page-number"))
            .map(Long::valueOf)
            .filter(e -> e > 0)
            .orElse(DEFAULT_PAGE_NUMBER);

        Long amount = Optional.ofNullable(req.getParameter("amount"))
            .map(Long::valueOf)
            .orElse(DEFAULT_AMOUNT);

        String type = Optional.ofNullable(req.getParameter("type"))
            .orElse(DEFAULT_TYPE);

        String keyword = Optional.ofNullable(req.getParameter("keyword"))
            .orElse(DEFAULT_KEYWORD);

        return Criteria.builder()
            .pageNumber(pageNumber)
            .amount(amount)
            .type(type)
            .keyword(keyword)
            .build();
    }
}
