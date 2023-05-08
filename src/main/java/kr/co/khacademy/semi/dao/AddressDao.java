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
import kr.co.khacademy.semi.model.Criteria;

public class AddressDao {

    private static final AddressDao instance = new AddressDao();

    private static final String INSERT_SQL = "INSERT INTO address VALUES (DEFAULT, DEFAULT, ?, ?, ?, ?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM address";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM address WHERE id = ? ";
    private static final String UPDATE_BY_ID_SQL = "UPDATE address SET WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM address WHERE id = ?";

    private Object resultSet;

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
    public List<Address> read(Long start, Long end) throws SQLException{
        List<Address> addresses = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL)){
                preparedStatement.setLong(1, start);
                preparedStatement.setLong(2, end);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    while(resultSet.next()) {
                        Address address = Address.of(resultSet);
                        addresses.add(address);
                    }
                    return Collections.unmodifiableList(addresses);
                }
            }
        }
    }
public List<String> getPageNavi(Criteria criteria) throws SQLException{
        
        Long recordTotalCount = getRecordCount(criteria.getKeyword());

        Long pageNumber = criteria.getPageNumber();
        Long recordCountPerPage = criteria.getAmount();
        Long naviCountPerPage = 10L;

        Long pageTotalCount;

        if (recordTotalCount % recordCountPerPage > 0) {
            pageTotalCount = recordTotalCount / recordCountPerPage + 1;
        } else {
            pageTotalCount = recordTotalCount / recordCountPerPage;
        }
        
        if (pageNumber < 1) {
            pageNumber = 1L;
        } else if (pageNumber > pageTotalCount) {
            pageNumber = pageTotalCount;
        }
        Long startNavi = (((pageNumber - 1) / naviCountPerPage) * naviCountPerPage) + 1;
        Long endNavi = startNavi + (naviCountPerPage-1);
        
        if (endNavi > pageTotalCount) {
            endNavi = pageTotalCount;
        }
        Boolean needPrev = true;
        Boolean needNext = true;
        
        if (startNavi == 1) {needPrev = false;}
        if (endNavi == pageTotalCount) {needNext = false;}
        
        List<String> list = new ArrayList<>();
        
        if (needPrev) {
            list.add("<");
        }
        for (Long i = startNavi; i <= endNavi; i++) {
            list.add(""+i);
        }
        if (needNext) {
            list.add(">");
        }
        return list;
    }
public Long getRecordCount(String search) throws SQLException {
    try (Connection connection = DataSource.getConnection()){
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)){
            preparedStatement.setString(1, "%"+search+"%");
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                resultSet.next();
                return resultSet.getLong(1);
            }
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
                preparedStatement.setLong(1, id);
                
           
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Address.of(resultSet); 
                        
                    }     
                    throw new SQLException();
                }
                
            }
        }
    }

    public void update(Address address) throws SQLException {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
            preparedStatement.setString(1, address.getName());
            preparedStatement.setString(2, address.getPostAddress());

            int result = preparedStatement.executeUpdate();
            if (result==0) {
                throw new SQLException();
            }
            connection.commit();
        }
    }

    public void delete(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException();
            }
            connection.commit();
        }
    }
}