package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Inquiry;

public class InquiryDao {
    private static final InquiryDao instance = new InquiryDao();
    
    private static final String INSERT_SQL = "INSERT INTO inquiry VALUES(default,default,?,?)";
    
    
    public InquiryDao getInstance() {
        return instance;
    }
    
    public void create(Inquiry inquiry) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){
                preparedStatement.setString(1, inquiry.getTitle());
                preparedStatement.setString(2, inquiry.getContents());
                
                if(preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

}
