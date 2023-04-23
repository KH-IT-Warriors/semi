package kr.co.khacademy.semi.conf;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {

    Connection getConnection();
}
