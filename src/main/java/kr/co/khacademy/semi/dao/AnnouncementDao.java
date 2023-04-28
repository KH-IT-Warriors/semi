package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Announcement;

public class AnnouncementDao {
    private static final AnnouncementDao instance = new AnnouncementDao();
    
    private static final String INSERT_SQL = "INSERT INTO announcement VALUES (default,default,?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM announcement";
    private static final String UPDATE_SQL = "UPDATE announcement SET title = ?, contents = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE announcement WHERE id =?";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM announcement WHERE id = ?";
    
    public static AnnouncementDao getInstance() {
        return instance;
    }
    
    public void create(Announcement announcement) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)){
                preparedStatement.setString(1, announcement.getTitle());
                preparedStatement.setString(1, announcement.getTitle());
                preparedStatement.setLong(3, announcement.getId());
                
                if(preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }
    
    
    
}
