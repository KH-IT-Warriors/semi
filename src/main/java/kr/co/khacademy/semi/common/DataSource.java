package kr.co.khacademy.semi.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

    private static final HikariConfig hikariConfig = new HikariConfig("/datasource.properties");
    private static final HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);

    private DataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
