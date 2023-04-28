package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Faq;

public class FaqDao {

    private static final FaqDao instance = new FaqDao();

    private static final String INSERT_FAQ_SQL = "INSERT INTO faq VALUES(?, ?)";
    private static final String SELECT_FAQ_SQL = "SELECT * FROM faq";
    private static final String UPDATE_FAQ_SQL = "UPDATE faq SET title = ?, contents = ? WHERE id = ?";
    private static final String DELETE_FAQ_SQL = "DELETE faq WHERE id =?";
    private static final String FIND_BY_ID_FAQ_SQL = "SELECT * FROM faq WHERE id = ?";

    public static FaqDao getInstance() {
        return instance;
    }

    public Boolean create(Faq faq) {
        try(Connection connection = DataSource.getConnection();){
            try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FAQ_SQL);){
                preparedStatement.setString(1, faq.getTitle());
                preparedStatement.setString(2, faq.getContents());

                int result = preparedStatement.executeUpdate();

                if(result == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
        }catch (SQLException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

        public List<Faq> read(){
            List<Faq> faqList = new ArrayList<>();
            try(Connection connection = DataSource.getConnection();){
                try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAQ_SQL);){
                    try(ResultSet resultSet = preparedStatement.executeQuery();){
                        while(resultSet.next()) {
                            Faq faq = Faq.of(resultSet);
                            faqList.add(faq);
                        }
                    }
                }
            }catch (SQLException ignored) {
            }
            return Collections.unmodifiableList(faqList);

        }

        public Boolean update(Faq faq) {
            try(Connection connection = DataSource.getConnection();){
                try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FAQ_SQL)){
                    preparedStatement.setString(1, faq.getTitle());
                    preparedStatement.setString(2, faq.getContents());
                    preparedStatement.setLong(3, faq.getId());
                    
                    int result = preparedStatement.executeUpdate();
                    if(result == 0) {
                        connection.rollback();
                        throw new SQLException();
                    }
                }
            }catch (SQLException e) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }
        
        public Boolean delete(Faq faq) {
            try(Connection connection = DataSource.getConnection();){
                try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FAQ_SQL)){
                    preparedStatement.setLong(1, faq.getId());
                    
                    int result = preparedStatement.executeUpdate();
                    if(result == 0) {
                        connection.rollback();
                        throw new SQLException();
                    }
                }
            }catch (SQLException e) {
                return Boolean.FALSE;
            }
            return Boolean.TRUE;
        }

        public Optional<Faq> read(Long id){
            try(Connection connection = DataSource.getConnection();){
                try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_FAQ_SQL);){
                    preparedStatement.setLong(1, id);
                    try(ResultSet resultSet = preparedStatement.executeQuery();){
                        if(resultSet.next()) {
                            return Optional.of(Faq.of(resultSet));
                        }
                    }
                }
            }catch (SQLException ignored) {
            }
            return Optional.empty();
        }


    }














