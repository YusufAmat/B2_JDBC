package jdbc;

import java.sql.*;

public class _01Connection {

    /*
        Bir veri tabani
        veri tabani yolu, nerede, cloud, local
        baglanmak icin arayüz, MySQL Workbench

        JDBC
        java ile veri tabanlarina baglanma madülü

        Connection : Yol
        Statement  : firma
        ResultSet  : otobüs, data tasima

     */

    public static void main(String[] args) throws SQLException {
        // String url = "jdbc:mysql://[localhost| 105.12.167.214] : [PORT]/[database_name]";
        // jdbc ile mysql e baglandim, databe localde ve adi b2_db1
        String url = "jdbc:mysql://localhost:3306/b2_db1";
        String username = "root";
        String password = "root123456";

        // 1. veri tabanina yapilan connection
        Connection conn = DriverManager.getConnection(url, username, password);

        // 2. iletisim icin statement gerekli
        Statement stmt = conn.createStatement();

        // 3. data transfer icin resultset gerekli
        ResultSet rs = stmt.executeQuery("SELECT * from table1");
        while (rs.next()) {
            int id = rs.getInt(1);
            String firstName = rs.getString("first_name");
            String lastName = rs.getString(3);
            String gender = rs.getString(4);
            int age = rs.getInt(5);

            String str = String.format("%d\t%-20s%-20s%-10s%-5d", id, firstName, lastName, gender, age);
            System.out.println(str);

        }

        stmt.close();
        conn.close();
    }

}
