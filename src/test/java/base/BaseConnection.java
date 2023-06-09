package base;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.*;

public class BaseConnection {

    protected Connection conn;
    protected Statement statement;
    protected ResultSet resultSet;


    @BeforeTest
    public void beforeTest() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db1";
        String username = "root";
        String password = "root123456";
        conn = DriverManager.getConnection(url, username, password);
        //bunun sayesinde datalar arasinda geri gezinti yapilabilir
        statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        //statement = conn.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        conn.close();
    }
}
