package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _05SQLite {

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

    @Test
    public  void test1() throws SQLException {
        String sql = "SELECT id, first_name, last_name, age, length " +
                "FROM personel " +
                "LIMIT 50;";
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Object[] arr = {resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3),
                    resultSet.getInt(4), resultSet.getDouble(5)};

            System.out.printf("%-5d%-15s%-15s%-10d%5.2f\n",arr );
        }
    }
}


