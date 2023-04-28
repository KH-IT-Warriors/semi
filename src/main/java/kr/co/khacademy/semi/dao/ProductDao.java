package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Product;

public class ProductDao {

    private static final ProductDao instance = new ProductDao();
    
    private static final String INSERT_PRODUT_SQL ="INSERT INTO product (name, title, summary, detail, price, quantity, categoryId) "
            + "VALUE (?, ?, ?, ?, ?, ?, ?)";
    
    private static final String UPDATE_PRODUT_SQL = "UPDATE product SET name = ?, title = ?, summary = ?, detail = ?, "
            + "price = ?, quantity = ?, categoryId = ? WHERE = ? ";
                                   
   
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM product WHERE = ?";
    
    private static final String DELETE_PRODUCT_SQL = "DELETE product WHERE = ?";
    

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

    public Boolean create(Product product) {
        try (
                Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUT_SQL)
        ) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getTitle());
            preparedStatement.setString(3, product.getSummary());
            preparedStatement.setString(4, product.getDetail());
            preparedStatement.setLong(5, product.getPrice());
            preparedStatement.setLong(6, product.getQuantity());
            preparedStatement.setLong(7, product.getCategoryId());
       
            int result = preparedStatement.executeUpdate();
            
            if (result == 0) {
                connection.rollback();
                throw new SQLException();
            }
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
 
    }

    
    
    public Optional<Product> read(Long id) {
      try (
              Connection connection = DataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)
              
       ) {
          preparedStatement.setLong(1, id);
          try (ResultSet resultSet = preparedStatement.executeQuery()) {
          resultSet.next();
          return Optional.ofNullable(Product.of(resultSet));
         }  
      } catch (SQLException e) {
         throw new IllegalArgumentException();
      }
      

    }

    
    
    
    public List<Product> read() {
        return null;
    }

    public boolean update(Product product) {
      try( 
              Connection connection = DataSource.getConnection();
              PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUT_SQL);
    ) {
          preparedStatement.setString(1,product.getName());
          preparedStatement.setString(2,product.getTitle());
          preparedStatement.setString(3, product.getSummary());
          preparedStatement.setString(4, product.getDetail());
          preparedStatement.setLong(5, product.getPrice());
          preparedStatement.setLong(6, product.getQuantity());
          preparedStatement.setLong(7, product.getCategoryId());
          preparedStatement.setLong(8, product.getId());
          
          int result = preparedStatement.executeUpdate();
          
          if(result == 0) {
              connection.rollback();
              throw new SQLException();
          }
      } catch (SQLException e) {
          return Boolean.FALSE;
      }
      return Boolean.TRUE;
    }

    
    
    public void delete(Long id) {
      try (
           Connection connection = DataSource.getConnection();
           PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL)       
       ) {
          preparedStatement.setLong(1, id);
          int result = preparedStatement.executeUpdate();
          
          if (result == 0) {
             connection.rollback();
             throw new SQLException();
          }
          
      } catch (SQLException e) {
        throw new IllegalArgumentException();
      } 
        
    }
}
