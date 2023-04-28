package kr.co.khacademy.semi.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Product {

    Long id;
    String name;
    String title;
    String summary;
    String detail;
    Long price;
    Long quantity;
    Long categoryId;
    
    

    public static Product of(HttpServletRequest req) {
        return Product.builder()
            .build();
    }
    
    
}
