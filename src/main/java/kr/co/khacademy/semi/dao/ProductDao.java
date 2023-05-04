package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Criteria;
import kr.co.khacademy.semi.model.Product;

public class ProductDao {

    private static final ProductDao instance = new ProductDao();

    private static final String INSERT_SQL =
            "INSERT INTO product VALUES (DEFAULT, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BY_ID_SQL = "SELECT * FROM product WHERE id = ?";

    private static final String SELECT_SQL = "SELECT * FROM product";

<<<<<<< HEAD
=======
    private static final String SELECT_BOUND_SQL = "SELECT * FROM(SELECT product.*,row_number()"
            +"OVER(ORDER BY id DESC)rn FROM product)idrn WHERE rn BETWEEN ? AND ?";
    
    
    private static final String SELECT_NAME_COUNT_SQL = "SELECT COUNT(*) FROM PRODUCT WHERE NAME LIKE ? ";
   
    
>>>>>>> 50081eb (페이징 Navi추가)
    private static final String UPDATE_BY_ID_SQL =
            "UPDATE product SET name = ?, price = ?, quantity = ?, category = ?, summary = ?, thumbnail_imageurl = ?, detail_imageurl = ?" +
                    "WHERE id = ?";

    private static final String DELETE_BY_ID_SQL = "DELETE FROM product WHERE id = ?";

    
    private ProductDao() {
    }

    
    public static ProductDao getInstance() {
        return instance;
    }

    public void create(Product product) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setLong(2, product.getPrice());
                preparedStatement.setLong(3, product.getQuantity());
                preparedStatement.setString(4, product.getCategory());
                preparedStatement.setString(5, product.getSummary());
                preparedStatement.setString(6, product.getThumbnailImageUrl());
                preparedStatement.setString(7, product.getDetailImageUrl());

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
     
    }

    public Product read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
                preparedStatement.setLong(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Product.of(resultSet);
                    }
                }
            }
        }
        throw new SQLException();
    }
    
<<<<<<< HEAD
=======
    public List<Product> read (Long start, Long end) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOUND_SQL);
                  ) {
                       preparedStatement.setLong(1, start);
                       preparedStatement.setLong(2, end);
               
                        try (ResultSet resultSet = preparedStatement.executeQuery()) {
                            List<Product> products = new ArrayList<>();
                            
                          while(resultSet.next()) {
                              products.add(Product.of(resultSet));                              
                            }
                         return Collections.unmodifiableList(products);                        
                  }
            }
        }   
        
    }
    
   
    private Long getRecordCount (String search) throws SQLException {
        try(Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NAME_COUNT_SQL)) {
                preparedStatement.setString(1, '%' + search + '%');
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        resultSet.next();
                        return resultSet.getLong(1);                    
                }
            }
            
        }
        
    }
    
 
           
    public List<String> getPageNavi(Criteria criteria) throws SQLException{
        
        Long pageNumber = criteria.getPageNumber();
        
        Long recordTotalCount = 0L;
        recordTotalCount = getRecordCount(criteria.getKeyword());
        
        Long recordCountPerPage = criteria.getAmount();
        Long naviCountPerPage = 10L;
        
        Long pageTotalCount;
        
        if (recordTotalCount % recordCountPerPage > 0) {
            pageTotalCount = recordTotalCount / recordCountPerPage+1;
        } else {
            pageTotalCount = recordTotalCount / recordCountPerPage;
        }
        
        if (pageNumber < 1) {
              pageNumber = 1L;
        } else if (pageNumber > pageTotalCount ) {
              pageNumber = pageTotalCount;
        }
        
        Long startNavi = (((pageNumber-1) / naviCountPerPage) * naviCountPerPage)+1;
        Long endNavi = startNavi + (naviCountPerPage-1);
        
        if (endNavi > pageTotalCount) {
            endNavi = pageTotalCount;
        }
        
        boolean needPrev = true;
        boolean needNext = true;
        
        if(startNavi == 1) {needPrev = false;}
        if(endNavi == pageTotalCount) {needNext = false;}
        
        List<String> list = new ArrayList<>();
        
        if (needPrev) {
            list.add("<");
        }
        
        for (Long i = startNavi; i < endNavi; i ++) {
            list.add("i");
        }
        
        if (needNext) {
            list.add(">");
        }
        
        return list;
        
        
    }
        
        
      
>>>>>>> 50081eb (페이징 Navi추가)

    public List<Product> read() throws SQLException {
        List<Product> products = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()) {
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                while (resultSet.next()) {
                    products.add(Product.of(resultSet));
                }
                return Collections.unmodifiableList(products);
            }
        }
    }
<<<<<<< HEAD
=======
    
>>>>>>> 50081eb (페이징 Navi추가)

    public void update(Product product) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL);) {
                preparedStatement.setString(1, product.getName());
                preparedStatement.setLong(2, product.getPrice());
                preparedStatement.setLong(3, product.getQuantity());
                preparedStatement.setString(4, product.getCategory());
                preparedStatement.setString(5, product.getSummary());
                preparedStatement.setString(6, product.getThumbnailImageUrl());
                preparedStatement.setString(7, product.getDetailImageUrl());
                preparedStatement.setLong(8, product.getId());

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

    public void delete(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
                preparedStatement.setLong(1, id);

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }

        }
    }

}
