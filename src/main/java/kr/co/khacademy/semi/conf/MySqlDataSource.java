package kr.co.khacademy.semi.conf;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MySqlDataSource implements DataSource {

    private static final MySqlDataSource instance = new MySqlDataSource();

    private static final HikariConfig hikariConfig = new HikariConfig("/datasource.properties");
    private static final HikariDataSource dataSource = new HikariDataSource(hikariConfig);

    private MySqlDataSource() {
    }

    public static MySqlDataSource getInstance() {
        return instance;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
