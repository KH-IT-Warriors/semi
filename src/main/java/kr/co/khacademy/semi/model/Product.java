package kr.co.khacademy.semi.model;

import lombok.Builder;
import lombok.Value;

import javax.servlet.http.HttpServletRequest;

@Value(staticConstructor = "of")
@Builder
public class Product {

    private Long id;
    private String name;
    private String title;
    private String summary;
    private String detail;
    private Long price;
    private Long quantity;
    private Long categoryId;
    
    

    public static Product of(HttpServletRequest req) {
        return Product.builder()
            .id(Long.parseLong(req.getParameter("id")))
            .build();
    }
}
