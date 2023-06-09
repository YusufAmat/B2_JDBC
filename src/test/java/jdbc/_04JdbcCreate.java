package jdbc;

import base.BaseConnection;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _04JdbcCreate extends BaseConnection {

    @Test
    public  void test1() throws SQLException {

        String sql = "CREATE TABLE mytable (" +
                "first_name VARCHAR(50)," +
                "last_name VARCHAR(50)," +
                "age INTEGER" +
                ");";

        System.out.println(statement.execute(sql));
    }

    @Test
    public void test2() throws SQLException {
        String sql = "INSERT INTO mytable(first_name, last_name, age) VALUES('Ali', 'Ali1', 12)";
        int num = statement.executeUpdate(sql);
        System.out.println(num);
    }

    @Test
    public void test3() throws SQLException {
        String sql = "INSERT INTO mytable(first_name, last_name, age) VALUES('Veli', 'Veli1', 12)," +
                "('Veli', 'Veli1', 12)," +
                "('Veli', 'Veli2', 22)," +
                "('Veli', 'Veli3', 32)," +
                "('Veli', 'Veli4', 42)";
        int num = statement.executeUpdate(sql);
        System.out.println(num);
    }

    @Test
    public void test4() throws SQLException {
        String sql = "select * from mytable;";
        resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.printf("%-15s%-15s%-10d\n",resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3) );
        }
    }
}


