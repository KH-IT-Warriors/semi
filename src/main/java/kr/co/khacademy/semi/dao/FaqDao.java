package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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


}
