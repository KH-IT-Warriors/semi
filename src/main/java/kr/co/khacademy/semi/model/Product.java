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
    
   public static Product of(ResultSet resultSet) {
     
       try {
       return Product.builder()
               .id(resultSet.getLong("id"))
               .name(resultSet.getString("name"))
               .title(resultSet.getString("title"))
               .summary(resultSet.getString("summary"))
               .detail(resultSet.getString("detail"))
               .price(resultSet.getLong("price"))
               .quantity(resultSet.getLong("quantity"))
               .categoryId(resultSet.getLong("categoryId"))
               .build();
       
       } catch (SQLException e) {
          throw new IllegalArgumentException();
       }
       
   }
}
