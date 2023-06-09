package jdbc;

import base.BaseConnection;
import org.testng.annotations.Test;
import utils.Utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;

public class _03JdbcMetaData extends BaseConnection {

    @Test
    public void test1() throws SQLException {
        // personel tablosu shirtsize lara göre ortalama yas, max yas, min yas yazdir
        String sql = "select shirtsize, avg(age), max(age), min(age) from db1.personel " +
                     "group by shirtsize;";

        resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            String shirtSize = resultSet.getString(1);
            double averageAge = resultSet.getDouble(2);
            int maxAge = resultSet.getInt(3);
            int minAge = resultSet.getInt(4);

            System.out.printf("%s\t%5.2f\t%d\t%d\n", shirtSize, averageAge, maxAge, minAge);
        }
    }

    @Test
    public void test2_MetaData() throws SQLException {
        // personel tablosu shirtsize lara göre ortalama yas, max yas, min yas yazdir
        String sql = "select shirtsize, avg(age), max(age), min(age) from db1.personel " +
                "group by shirtsize;";

        resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        for (int i = 1; i <=rsmd.getColumnCount() ; i++) {
            System.out.println(rsmd.getColumnName(i));
        }
    }

    @Test
    public void test3() throws SQLException {
        // tablo1 tablosunu alin, tablo basligini ilk satira ekleyin, altina datalari ekleyin

        String sql = "Select * from table1";

        resultSet = statement.executeQuery(sql);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();
        int[] columnSize = new int[columnCount];

        for (int i = 0; i < columnCount; i++) {
            columnSize[i] = rsmd.getColumnDisplaySize(i+1)/2;
        }

        for (int i = 0; i < columnCount ; i++) {
            System.out.printf("%-"+columnSize[i]+"s" , rsmd.getColumnName(i+1));
        }
        System.out.println();

        while (resultSet.next()) {
            for (int i = 0; i < columnCount; i++) {
                System.out.printf("%-" + columnSize[i] + "s", resultSet.getString(i + 1));
            }
            System.out.println();
        }
    }


    @Test
    public void test4(){
        String sql = "SELECT id, first_name, last_name, age FROM personel";
        List<List<String>> data = Utils.getTable(statement, sql);

        for (List<String> row : data) {
            System.out.println(row);
        }

    }


}
