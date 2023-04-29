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
        
        if(validateProductTitle(req.getParameter("title"))) {
            throw new IllegalArgumentException();
        }
        
        if(validateProductSummary(req.getParameter("summary"))) {
            throw new IllegalArgumentException();
        }
        
        if (validateProductDetail(req.getParameter("detail"))) {
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
    

    
    private static boolean validateProductTitle(String title) {   
        return (title.length() >= 3) && (title.length() <= 200);
    }
    
    private static boolean validateProductSummary(String summary) {
        return (summary.length() >= 30);     
    }
    
    private static boolean validateProductDetail (String detail) {
        return (detail.length() >= 150);
    }
    
  
   public static Product of (ResultSet resultSet){
       try {  
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
       } catch (SQLException e) {
           throw new IllegalArgumentException();
       } 
   }
}
