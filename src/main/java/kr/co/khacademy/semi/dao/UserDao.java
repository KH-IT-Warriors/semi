package kr.co.khacademy.semi.dao;

import kr.co.khacademy.semi.common.DataSource;
import kr.co.khacademy.semi.model.Criteria;
import kr.co.khacademy.semi.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final UserDao instance = new UserDao();

    private UserDao() {
    }

    public static UserDao getInstance() {
        return instance;
    }

    private static final String INSERT_ACCOUNT_SQL =
        "INSERT INTO accounts VALUES (0, DEFAULT, ?, ?, ?)";
    private static final String INSERT_PROFILE_SQL =
        "INSERT INTO profiles VALUES (?, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT, null)";
    private static final String SELECT_ALL_NORMAL_USER_SQL =
        "SELECT A.*, P.*, R.* FROM (SELECT TMP.*, ROW_NUMBER() OVER(ORDER BY TMP.id ASC) N FROM accounts TMP WHERE TMP.role_id = 1) A JOIN profiles P ON P.account_id = A.id JOIN roles R ON A.role_id = R.id WHERE N BETWEEN ? AND ?";
    private static final String SELECT_ALL_ADMIN_USER_SQL =
        "SELECT A.*, P.*, R.* FROM (SELECT TMP.*, ROW_NUMBER() OVER(ORDER BY TMP.id ASC) N FROM accounts TMP WHERE TMP.role_id != 1) A JOIN profiles P ON P.account_id = A.id JOIN roles R ON A.role_id = R.id WHERE N BETWEEN ? AND ?";
    private static final String SELECT_USER_SQL =
        "SELECT * FROM profiles P JOIN accounts A ON P.account_id = A.id JOIN roles R ON A.role_id = R.id WHERE A.id = ?";
    private static final String UPDATE_ACCOUNT_SQL =
        "UPDATE accounts SET status_id = ?, role_id = ? WHERE id = ?";
    private static final String UPDATE_ACCOUNT_WITH_PW_SQL =
        "UPDATE accounts SET status_id = ?, role_id = ?, password = ? WHERE id = ?";
    private static final String UPDATE_PROFILE_SQL =
        "UPDATE profiles SET name = ?, phone_number = ?, email = ?, mileage = ?, grade_id = ? WHERE account_id = ?";
    private static final String DELETE_ADMIN_ACCOUNT_SQL =
        "DELETE FROM accounts WHERE id = ?";
    private static final String DELETE_ADMIN_PROFILE_SQL =
        "DELETE FROM profiles WHERE account_id = ?";

    private static final String SELECT_NORMAL_USER_COUNT_SQL =
        "SELECT count(*) FROM accounts JOIN profiles ON id = account_id WHERE role_id = 1 AND name LIKE ?";
    private static final String SELECT_ADMIN_USER_COUNT_SQL =
        "SELECT count(*) FROM accounts JOIN profiles ON id = account_id WHERE role_id != 1 AND name LIKE ?";
    private static final String SEARCH_NORMAL_USER_SQL =
        "SELECT A.*, P.*, R.* FROM (SELECT TMP.*, ROW_NUMBER() OVER(ORDER BY TMP.id ASC) N FROM accounts TMP WHERE TMP.role_id = 1) A JOIN (SELECT * FROM profiles WHERE name LIKE ?) P ON P.account_id = A.id JOIN roles R ON A.role_id = R.id WHERE N BETWEEN ? AND ?";
    private static final String SEARCH_ADMIN_USER_SQL =
        "SELECT A.*, P.*, R.* FROM (SELECT TMP.*, ROW_NUMBER() OVER(ORDER BY TMP.id ASC) N FROM accounts TMP WHERE TMP.role_id != 1) A JOIN (SELECT * FROM profiles WHERE name LIKE ?) P ON P.account_id = A.id JOIN roles R ON A.role_id = R.id WHERE N BETWEEN ? AND ?";

    private static final String UPDATE_PROFILE_IMAGE_SQL =
        "UPDATE profiles SET profile_image = ? WHERE account_id = ?";


    public void create(User user) throws SQLException {
        try (Connection connection = DataSource.getConnection();) {
            Long generatedId;
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACCOUNT_SQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setLong(1, user.getAccount().getRoleId());
                preparedStatement.setString(2, user.getAccount().getUsername());
                preparedStatement.setString(3, user.getAccount().getPassword());
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    resultSet.next();
                    generatedId = resultSet.getLong(1);
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROFILE_SQL)) {
                preparedStatement.setLong(1, generatedId);
                preparedStatement.setString(2, user.getProfile().getName());
                preparedStatement.setString(3, user.getProfile().getPhoneNumber());
                preparedStatement.setString(4, user.getProfile().getEmail());
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
            connection.commit();
        }
    }

    public List<User> readNormalUser(Long start, Long end) throws SQLException {
        return getUsers(start, end, SELECT_ALL_NORMAL_USER_SQL);
    }

    public List<User> readAdminUser(Long start, Long end) throws SQLException {
        return getUsers(start, end, SELECT_ALL_ADMIN_USER_SQL);
    }

    private List<User> getUsers(Long start, Long end, String selectSQL) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectSQL);) {
            preparedStatement.setLong(1, start);
            preparedStatement.setLong(2, end);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(User.of(resultSet));
                }
                return users;
            }
        }
    }

    public User read(Long id) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_SQL)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return User.of(resultSet);
                } else {
                    throw new SQLException();
                }
            }
        }
    }

    public void update(User user) throws SQLException {
        try (Connection connection = DataSource.getConnection()) {
            if (user.getAccount().getPassword().isEmpty()) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_SQL)) {
                    preparedStatement.setLong(1, user.getAccount().getStatusId());
                    preparedStatement.setLong(2, user.getAccount().getRoleId());
                    preparedStatement.setLong(3, user.getAccount().getId());
                    if (preparedStatement.executeUpdate() == 0) {
                        connection.rollback();
                        throw new SQLException();
                    }
                }
            } else {
                try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ACCOUNT_WITH_PW_SQL)) {
                    preparedStatement.setLong(1, user.getAccount().getStatusId());
                    preparedStatement.setLong(2, user.getAccount().getRoleId());
                    preparedStatement.setString(3, user.getAccount().getPassword());
                    preparedStatement.setLong(4, user.getAccount().getId());
                    if (preparedStatement.executeUpdate() == 0) {
                        connection.rollback();
                        throw new SQLException();
                    }
                }
            }

            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_SQL)) {
                preparedStatement.setString(1, user.getProfile().getName());
                preparedStatement.setString(2, user.getProfile().getPhoneNumber());
                preparedStatement.setString(3, user.getProfile().getEmail());
                preparedStatement.setLong(4, user.getProfile().getMileage());
                preparedStatement.setLong(5, user.getProfile().getGradeId());
                preparedStatement.setLong(6, user.getAccount().getId());
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
            connection.commit();
        }
    }

    public void forceDelete(Long targetId) throws SQLException {
        try (Connection connection = DataSource.getConnection();) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_PROFILE_SQL)) {
                preparedStatement.setLong(1, targetId);
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADMIN_ACCOUNT_SQL)) {
                preparedStatement.setLong(1, targetId);
                if (preparedStatement.executeUpdate() == 0) {
                    connection.rollback();
                    throw new SQLException();
                }
            }
            connection.commit();
        }
    } // TODO: 일반 유저 강제 삭제는 나중에 테이블이 다 만들어진 후에 만들기

    public List<User> searchNormalUser(String keyword, Long start, Long end) throws SQLException {
        return getUsers(keyword, start, end, SEARCH_NORMAL_USER_SQL);
    }

    public List<User> searchAdminUser(String keyword, Long start, Long end) throws SQLException {
        return getUsers(keyword, start, end, SEARCH_ADMIN_USER_SQL);
    }

    private List<User> getUsers(String keyword, Long start, Long end, String searchSQL) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(searchSQL)) {
            System.out.println("%"+keyword+"%");
            preparedStatement.setString(1, "%"+keyword+"%");
            preparedStatement.setLong(2, start);
            preparedStatement.setLong(3, end);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                List<User> users = new ArrayList<>();
                while (resultSet.next()) {
                    users.add(User.of(resultSet));
                }
                return users;
            }
        }
    }

    public List<String> getPageNavi(Criteria criteria) throws SQLException {
        Long recordTotalCount;
        Long currentPage = criteria.getPageNumber();
        if (criteria.getType().equals("normal")) {
            recordTotalCount = getNormalUserCount(criteria.getKeyword());
        } else {
            recordTotalCount = getAdminUserCount(criteria.getKeyword());
        }
        Long pageTotalCount = (long) Math.ceil((double) recordTotalCount / criteria.getAmount());

        if (currentPage > pageTotalCount) currentPage = pageTotalCount;

        Long startNavi = (currentPage-1) / 10 * 10 + 1;
        Long endNavi = startNavi + (9);

        if (endNavi > pageTotalCount) {
            endNavi = pageTotalCount;
        }

        List<String> navi = new ArrayList<>();
        if (startNavi.equals(1L)) {
        } else {
            navi.add("<");
        }
        for (Long i = startNavi; i <= endNavi; i++) {
            navi.add(String.valueOf(i));
        }
        if (endNavi.equals(pageTotalCount)) {
        } else {
            navi.add(">");
        }
        return navi;
    }

    private Long getNormalUserCount(String keyword) throws SQLException {
        return getCount(keyword, SELECT_NORMAL_USER_COUNT_SQL);
    }
    private Long getAdminUserCount(String keyword) throws SQLException {
        return getCount(keyword, SELECT_ADMIN_USER_COUNT_SQL);
    }

    private Long getCount(String keyword, String selectCountSQL) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(selectCountSQL);) {
            preparedStatement.setString(1, "%"+keyword+"%");
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    return resultSet.getLong(1);
                } else {
                    throw new SQLException();
                }
            }
        }
    }

    public void uploadImage(Long id, String systemName) throws SQLException {
        try (Connection connection = DataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PROFILE_IMAGE_SQL);) {
            preparedStatement.setString(1, systemName);
            preparedStatement.setLong(2, id);
            if (preparedStatement.executeUpdate() == 0) {
                connection.rollback();
                throw new SQLException();
            }
            connection.commit();
        }
    }
}