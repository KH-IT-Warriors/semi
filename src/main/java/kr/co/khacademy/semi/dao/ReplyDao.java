package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplyDao {

    private static final ReplyDao instance = new ReplyDao();

    private static final String INSERT_SQL =
        "INSERT INTO reply VALUES (DEFAULT, ?, ?, ?, DEFAULT, DEFAULT)";

    private static final String SELECT_BY_PRODUCT_ID_SQL =
        "SELECT * FROM reply WHERE productId = ?";

    private static final String UPDATE_BY_ID_SQL =
        "UPDATE reply SET contents = ?, modified = DEFAULT WHERE id = ?";

    private static final String DELETE_BY_ID_SQL =
        "DELETE FROM reply WHERE id = ?";

    private ReplyDao() {
    }

    public static ReplyDao getInstance() {
        return instance;
    }

    public void create(Reply reply) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SQL)) {
                preparedStatement.setLong(1, reply.getAccountId());
                preparedStatement.setLong(2, reply.getProductId());
                preparedStatement.setString(3, reply.getContents());

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

    public List<Reply> read(Reply reply) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_PRODUCT_ID_SQL)) {

                preparedStatement.setLong(1, reply.getProductId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {

                    List<Reply> replies = new ArrayList<>();
                    while (resultSet.next()) {
                        Reply result = Reply.of(resultSet);
                        replies.add(result);
                    }
                    return Collections.unmodifiableList(replies);
                }
            }
        }
    }

    public void update(Reply reply) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BY_ID_SQL)) {
                preparedStatement.setString(1, reply.getContents());
                preparedStatement.setLong(2, reply.getId());

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
