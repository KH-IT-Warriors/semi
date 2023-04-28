package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

    public List<Announcement> read() throws SQLException{
        List<Announcement> AnnouncementList = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()){
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                    ResultSet resultSet = preparedStatement.executeQuery();){
                while (resultSet.next()) {
                    Announcement announcement = Announcement.of(resultSet);
                    AnnouncementList.add(announcement);
                }
            }
        }
        return Collections.unmodifiableList(AnnouncementList);
    }
    
    public Announcement read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)){
                preparedStatement.setLong(1, id);
                try(ResultSet resultSet = preparedStatement.executeQuery()){
                    if(resultSet.next()) {
                        return Announcement.of(resultSet);
                    }
                }
            }
        }
        throw new SQLException();
    }
    
    

}
