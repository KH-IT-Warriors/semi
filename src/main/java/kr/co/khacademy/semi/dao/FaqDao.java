package kr.co.khacademy.semi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Faq;

public class FaqDao {

    private static final FaqDao instance = new FaqDao();

    private static final String INSERT_SQL = "INSERT INTO faq VALUES (default,default,?, ?)";
    private static final String SELECT_SQL = "SELECT * FROM faq";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM faq WHERE id = ?";
    private static final String UPDATE_BY_ID_SQL = "UPDATE faq SET title = ?, contents = ? WHERE id = ?";
    private static final String DELETE_BY_ID_SQL = "DELETE faq WHERE id =?";

    public static FaqDao getInstance() {
        return instance;
    }

    public void create(Faq faq) throws SQLException {
        try (Connection connection = DataSource.getConnection()){
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                preparedStatement.setString(1, faq.getTitle());
                preparedStatement.setString(2, faq.getContents());

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

    public List<Faq> read() throws SQLException {
        List<Faq> faqList = new ArrayList<>();
        try (Connection connection = DataSource.getConnection()) {
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                    ResultSet resultSet = preparedStatement.executeQuery()
            ){
                while (resultSet.next()) {
                    Faq faq = Faq.of(resultSet);
                    faqList.add(faq);
                }
                return Collections.unmodifiableList(faqList);
            }
        }
    }

    public Faq read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Faq.of(resultSet);
                    }
                    throw new SQLException();
                }
            }
        }
    }

    public void update(Faq faq) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
                preparedStatement.setString(1, faq.getTitle());
                preparedStatement.setString(2, faq.getContents());
                preparedStatement.setLong(3, faq.getId());

                if (preparedStatement.executeUpdate() == 0) {
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