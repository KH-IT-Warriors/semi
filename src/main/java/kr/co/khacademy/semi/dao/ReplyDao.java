package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Product;
import kr.co.khacademy.semi.model.Reply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReplyDao {

    private static final ReplyDao instance = new ReplyDao();

    private static final String INSERT_REPLY_SQL =
        "INSERT INTO reply(parent_reply_id, account_id, contents) VALUES (?, ?, ?)";

    private static final String UPDATE_REPLY_SQL =
        "UPDATE reply SET contents = ? ,created= DEFAULT WHERE id = ?";

    private static final String DELETE_REPLY_SQL =
        "DELETE FROM reply WHERE id = ?";

    private static final String READ_REPLY_SQL =
        "SELECT * FROM reply";

    private ReplyDao() {
    }

    public static ReplyDao getInstance() {
        return instance;
    }


    public void create(Reply reply) throws SQLException {
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
        }
    }

    public Reply read(Long id) {

    }

    public List<Reply> read() throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(READ_REPLY_SQL);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                List<Reply> list = new ArrayList<>();
                while (resultSet.next()) {
                    Reply reply = Reply.of(resultSet);
                    list.add(reply);
                }
                return list;
            }
        }
    }

    public void update(Reply reply) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REPLY_SQL)) {
                preparedStatement.setString(1, reply.getContents());
                preparedStatement.setLong(2, reply.getId());

                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
        }

    }

    public void delete(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REPLY_SQL)) {
                preparedStatement.setLong(1, id);

                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }

            }
        }
    }
}
