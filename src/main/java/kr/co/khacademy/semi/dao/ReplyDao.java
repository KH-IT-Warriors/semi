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
        "INSERT INTO reply VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT)";

    private static final String SELECT_SQL =
        "SELECT * FROM reply";

    private static final String UPDATE_BY_ID_SQL =
        "UPDATE reply SET contents = ?, created = DEFAULT WHERE id = ?";

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
                preparedStatement.setLong(1, reply.getParentReplyId());
                preparedStatement.setLong(2, reply.getAccountId());
                preparedStatement.setLong(3, reply.getProductId());
                preparedStatement.setString(4, reply.getContents());

                if (preparedStatement.executeUpdate() == 0) {
                    throw new SQLException();
                }
                connection.commit();
            }
        }
    }

    public List<Reply> read() throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SQL);
                ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                List<Reply> replies = new ArrayList<>();
                while (resultSet.next()) {
                    Reply reply = Reply.of(resultSet);
                    replies.add(reply);
                }
                return Collections.unmodifiableList(replies);
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
