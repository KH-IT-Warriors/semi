package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Product;
import kr.co.khacademy.semi.model.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ReplyDao {

    private static final ReplyDao instance = new ReplyDao();

    private static final String INSERT_REPLY_SQL =
        "INSERT INTO reply(parent_reply_id, account_id, contents) VALUES (?, ?, ?)";

    private static final String UPDATE_REPLY_SQL =
        "UPDATE reply SET contents = ? ,created= DEFAULT WHERE id = ?";
    private ReplyDao() {
    }

    public static ReplyDao getInstance() {
        return instance;
    }


    public void create(Reply reply) {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REPLY_SQL)) {
                preparedStatement.setLong(1, reply.getParentReplyId());
                preparedStatement.setLong(2, reply.getAccountId());
                preparedStatement.setString(3, reply.getContents());

                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reply read(Long id) {
        return null;
    }

    public List<Reply> read() {
        return null;
    }

    public void update(Reply reply) {

    }

    public void delete(Long id) {
    }
}
