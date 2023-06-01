package jdbc;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class _02jdbc {
    /*
        BeforeTest : Connection, Statement tanimlayin
        AfterTest  : connection, statement close edin
     */
    Connection conn;
    Statement statement;
    ResultSet resultSet;

    @BeforeTest
    public void beforeTest() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/b2_db1";
        String username = "root";
        String password = "root123456";
        conn = DriverManager.getConnection(url, username, password);
        statement = conn.createStatement();
    }

    @AfterTest
    public void afterTest() throws SQLException {
        statement.close();
        conn.close();
    }

    //personel tablasundaki genderlerin sayisini bulun
    @Test
    public void test01() throws SQLException {
        resultSet = statement
                .executeQuery("SELECT gender, COUNT(*) AS sayi FROM b2_db1.personel\n" +
                        "GROUP BY gender ORDER BY sayi desc;");
        while (resultSet.next()) {
            String gender = resultSet.getString(1);
            int count = resultSet.getInt(2);
            System.out.printf("%-20s:% d\n", gender, count);
        }
    }

    @Test
    public void test02() throws SQLException {
        resultSet = statement
                .executeQuery("SELECT gender, COUNT(*) AS sayi FROM b2_db1.personel\n" +
                        "GROUP BY gender " +
                        "UNION " +
                        "Select 'toplam', count(*) from personel " +
                        "ORDER BY sayi asc;");
        while (resultSet.next()) {
            String gender = resultSet.getString(1);
            int count = resultSet.getInt(2);
            System.out.printf("%-20s:% d\n", gender, count);
        }
    }

    // adinizi sayadinizi cinsiyet ve yasinizi table1 tablosuna ekleyin
    // sonra son kaydi select ile secip ekrana yazdirin
    @Test
    public void test03() throws SQLException {
        String sql = "INSERT INTO table1(first_name, last_name,gender,age) VALUES('yusuf', 'deneme', 'male', 33);";
        int i = statement.executeUpdate(sql);
        if (i>0){
            System.out.println("Kayit yapildi");
            resultSet = statement.executeQuery("Select * from table1 order by id desc limit 1;");
            while(resultSet.next()){
                String firstName = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                String gender = resultSet.getString(4);
                int age = resultSet.getInt(5);
                System.out.println(firstName + lastName);
            }
        }else{
            System.out.println("Kayit yapilamadi");
        }

    }
}
