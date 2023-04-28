package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Product;

public class ProductDao {

    private static final ProductDao instance = new ProductDao();
    
    private static final String INSERT_PROCUT_SQL = "";

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

    public Boolean create(Product product) {
        
        String sql = "insert into product (name, title, summary, detail, price, quantity, categoryId) value"
                + "(?, ?, ?, ?, ?, ?, ?)";
        
        
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
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
            }
        } catch (SQLException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
        
        
        
        
    }

    public Product read(Long id) {
        return null;
    }

    public List<Product> read() {
        return null;
    }

    public boolean update(Product product) {
        return false;
    }

    public void delete(Long id) {
    }
}
