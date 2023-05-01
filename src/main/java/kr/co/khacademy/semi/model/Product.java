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
        
        if(!validateProductTitle(req.getParameter("title"))) {
            throw new IllegalArgumentException();
        }
        
        return Product.builder()
            .name(req.getParameter("name"))
            .title(req.getParameter("title"))
            .summary(req.getParameter("summary"))
            .detail(req.getParameter("detail"))
            .price(Long.valueOf(req.getParameter("price")))
            .quantity(Long.valueOf(req.getParameter("quantity")))
            .categoryId(Long.valueOf(req.getParameter("category_id")))
            .build();
    }
    
    
    private static Boolean validateProductTitle(String title) {   
        return (3 <= title.length() ) && (title.length() <= 200);
    }
     
   public static Product of (ResultSet resultSet) throws SQLException{
      
       return Product.builder()
               .id(resultSet.getLong("id"))
               .name(resultSet.getString("name"))
               .title(resultSet.getString("title"))
               .summary(resultSet.getString("summary"))
               .detail(resultSet.getString("detail"))
               .price(resultSet.getLong("price"))
               .quantity(resultSet.getLong("quantity"))
               .categoryId(resultSet.getLong("category_id"))
               .build();    
   
   }
}
