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
    Long price;
    Long quantity;
    String category;
    String summary;
    String thumbnailImageUrl;
    String detailImageUrl;


    public static Product of(HttpServletRequest req) {
          
        return Product.builder()
            .name(req.getParameter("name"))
            .price(Long.valueOf(req.getParameter("price")))
            .quantity(Long.valueOf(req.getParameter("quantity")))
            .category(req.getParameter("category"))
            .summary(req.getParameter("summary"))
            .thumbnailImageUrl(null)
            .detailImageUrl(null)
            .build();
        
    }
    
    
     
   public static Product of (ResultSet resultSet) throws SQLException{
       
       return Product.builder()
               .id(resultSet.getLong("id"))
               .name(resultSet.getString("name"))
               .price(resultSet.getLong("price"))
               .quantity(resultSet.getLong("quantity"))
               .category(resultSet.getString("category"))
               .summary(resultSet.getString("summary"))
               .thumbnailImageUrl(null)
               .detailImageUrl(null)
               .build();    
   
   }
}
