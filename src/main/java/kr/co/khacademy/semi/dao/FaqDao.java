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
    private static final String UPDATE_FAQ_SQL = "UPDATE faq SET title = ? AND contents = ? WHERE id = ?";
    private static final String DELETE_FAQ_SQL = "DELETE faq WHERE id =?";
    private static final String FIND_BY_ID_FAQ_SQL = "SELECT * FROM faq WHERE id = ?";

    public static FaqDao getInstance() {
        return instance;
    }

    public Boolean create(Faq faq) {
        try(
                Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FAQ_SQL);
                ){
            preparedStatement.setString(1, faq.getTitle());
            preparedStatement.setString(2, faq.getContents());

            int result = preparedStatement.executeUpdate();

            if(result == 0) {
                connection.rollback();
                throw new SQLException();
            }
        }catch (SQLException e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
    
    public List<Faq> read(){
        try(
                Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_FAQ_SQL);
                ResultSet resultSet = preparedStatement.executeQuery();
                ){
            List<Faq> faqlist = new ArrayList<>();
            while(resultSet.next()) {
                Faq faq = Faq.builder()
                        .id(resultSet.getLong("id"))
                        .accountId(resultSet.getLong("account_id"))
                        .title(resultSet.getString("title"))
                        .contents(resultSet.getString("contents"))
                        .build();
                faqlist.add(faq);
            }
            return Collections.unmodifiableList(faqlist);
            
        }catch (SQLException e) {
            throw new IllegalArgumentException();
        }
    }

    public Optional<ResultSet> read(Long id){
        try(
                Connection connection = DataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_FAQ_SQL);
                ){
            preparedStatement.setLong(1, id);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
                    ){
                if(resultSet.next()) {
                return Optional.ofNullable(resultSet);
                }
            }
        }catch (SQLException e) {
            throw new IllegalArgumentException();
        }
        throw new IllegalArgumentException();
    }


}














