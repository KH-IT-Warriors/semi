package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Address;

public class AddressDao {

    private static final AddressDao instance = new AddressDao();

    private static final String INSERT_SQL = "INSERT INTO address VALUES (DEFAULT, DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM address WHERE id = ? ";
    private static final String UPDATE_BY_ID_SQL = "UPDATE address SET WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM address WHERE id = ?";

    private AddressDao() {
    }

    public static AddressDao getInstance() {
        return instance;
    }

    public void create(Address address) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                preparedStatement.setString(1, address.getName());
                preparedStatement.setString(2, address.getPostAddress());
                preparedStatement.setString(3, address.getRoadAddress());
                preparedStatement.setString(4, address.getLotAddress());
                preparedStatement.setString(5, address.getDetailAddress());
                
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

    public List<Address> read() throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {   
                List<Address> addresses = new ArrayList<>();
                while (resultSet.next()) {
                    Address address = Address.of(resultSet);
                    addresses.add(address);
                }
                return Collections.unmodifiableList(addresses);
            }
        }
    }

    public Address read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)) {
                preparedStatement.setLong(1, id);
                
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        
                    }
                    return Address.of(resultSet);
                }
            }
        }
        
    }

    public boolean update(Address address) throws SQLException {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
            preparedStatement.setString(1, address.getName());
            preparedStatement.setString(2, address.getPostAddress());

            return false;
        }
    }

    public void delete(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

        }
    }
}
