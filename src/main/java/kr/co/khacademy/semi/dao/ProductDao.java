package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.model.Product;

import java.util.List;

public class ProductDao {

    private static final ProductDao instance = new ProductDao();

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

    public boolean create(Product product) {
        return false;
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
