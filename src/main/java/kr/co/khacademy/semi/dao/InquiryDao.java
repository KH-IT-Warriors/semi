package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Inquiry;

public class InquiryDao {
    private static final InquiryDao instance = new InquiryDao();
    
    private static final String INSERT_SQL = "INSERT INTO inquiry VALUES(default,default,?,?)";
    private static final String SELECT_SQL = "SELECT * FROM inquiry";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM inquiry WHERE id = ?";
    private static final String UPDATE_BY_ID_SQL = "UPDATE inquiry SET title = ?, contents = ? WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE FROM inquiry WHERE id = ?";
    
    public static InquiryDao getInstance() {
        return instance;
    }
    
    public void create(Inquiry inquiry) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){
                preparedStatement.setString(1, inquiry.getTitle());
                preparedStatement.setString(2, inquiry.getContents());
                
                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }
    
    public List<Inquiry> read() throws SQLException{
        List<Inquiry> inquiryList = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()){
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                    ResultSet resultSet = preparedStatement.executeQuery()
            ){
                while (resultSet.next()) {
                    Inquiry inquiry = Inquiry.of(resultSet);
                    inquiryList.add(inquiry);
                }
                return Collections.unmodifiableList(inquiryList);
            }
        }
    }
    
    public Inquiry read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)){
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()){
                    if (resultSet.next()) {
                        return Inquiry.of(resultSet);
                    }
                    throw new SQLException();
                }
            }
        }
    }
    
    public void update(Inquiry inquiry) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)){
                preparedStatement.setString(1, inquiry.getTitle());
                preparedStatement.setString(2, inquiry.getContents());
                preparedStatement.setLong(3, inquiry.getId());
                
                if(preparedStatement.executeUpdate()==0){
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
