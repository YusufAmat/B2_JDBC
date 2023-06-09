package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _04JdbcCursor {

    protected Connection conn;
    protected Statement statement;
    protected ResultSet resultSet;


    @BeforeTest
    public void beforeTest() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/db1";
        String username = "root";
        String password = "root123456";
        conn = DriverManager.getConnection(url, username, password);
        statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        conn.close();
    }

    @Test
    public  void test1() throws SQLException {
        String sql = "SELECT first_name, last_name, age, length " +
                "FROM personel " +
                "ORDER BY first_name, last_name " +
                "LIMIT 50;";
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            Object[] arr = {resultSet.getString(1), resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getDouble(4)};

            System.out.printf("%-15s%-15s%-10d%5.2f\n",arr );
        }
    }

    @Test
    public  void test2() throws SQLException {
        String sql = "SELECT first_name, last_name, age, length " +
                "FROM personel " +
                "ORDER BY first_name, last_name " +
                "LIMIT 50;";
        resultSet = statement.executeQuery(sql);

        resultSet.next();  // cursor 1 kayit ileri gider
        Object[] arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.next();
        resultSet.next();
        resultSet.next();
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.previous();  // cursor 1 kayit geri gider
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.last();  // cursor son kayda gider
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.first(); // cursor ilk kayda gider
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.absolute(10); // cursor tablodaki 10. kayda gider
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.relative(3); // cursor bulundugu yerden baslamak üzere 3 kayit ileri gider
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

        resultSet.relative(-3); // cursor bulundugu yerden baslamak üzere 3 kayit ileri gider
        arr = new Object[]{resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getFloat(5)};
        System.out.printf("%-5d%-15s%-15s%-5d%.2f\n", arr);

    }
}


