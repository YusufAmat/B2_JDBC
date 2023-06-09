package jdbc;

import base.BaseSQLite;
import org.testng.annotations.Test;

import java.sql.*;

public class _06SQLite extends BaseSQLite {


    @Test
    public  void test1() throws SQLException {
        // personel, job tablosu: "Software Test Engineer" meslegi yapanlarin sayisi
        String sql = "select jobtitle, count(*)  from job " +
                "left join personel on job.id = personel.job_id " +
                "where jobtitle like '%Software Test Engineer%' " +
                "group by jobtitle;";

        resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            System.out.printf("%s\t%d\n", resultSet.getString(1), resultSet.getInt(2));
        }
    }

    @Test
    public  void test2() throws SQLException {

        String sql = "CREATE TABLE mytable (" +
                "first_name VARCHAR(50)," +
                "last_name VARCHAR(50)," +
                "age INTEGER" +
                ");";

        System.out.println(statement.execute(sql));
    }
}


