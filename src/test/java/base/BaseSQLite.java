package base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class BaseSQLite {

    protected Connection conn;
    protected Statement statement;
    protected ResultSet resultSet;


    @BeforeTest
    public void beforeTest() throws SQLException {
        String url = "jdbc:sqlite:src/test/resources/data.sqlite";
        conn = DriverManager.getConnection(url);
        statement = conn.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        conn.close();
    }
}
