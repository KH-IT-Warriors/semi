package kr.co.khacademy.semi.model;

import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;

@Value(staticConstructor = "of")
@Builder
public class Product {

    Long id;

    public static Product of(HttpServletRequest req) {
        return Product.builder()
            .id(Long.parseLong(req.getParameter("id")))
            .build();
    }
}
