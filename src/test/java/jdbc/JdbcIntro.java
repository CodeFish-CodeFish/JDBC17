package jdbc;

import org.junit.Test;

import java.sql.*;

public class JdbcIntro {

    @Test
    public void connectivityTest() throws SQLException {
        Connection connection = DriverManager.
                getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student",
                        "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        while (resultSet.next()) {//every single row
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4));
        }
    }

    @Test
    public void test2() throws SQLException {
        Connection connection = DriverManager.
                getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student",
                        "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("Select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();
//        System.out.println(metaData.getColumnName(1));
//        System.out.println(metaData.getColumnName(2));
//        System.out.println(metaData.getColumnName(3));
//        System.out.println(metaData.getColumnName(4));
//
            while (resultSet.next()){ //every row
                System.out.print("|");
                for (int i = 1; i <= metaData.getColumnCount(); i++){ // TO GET DATA FROM EVERY COLUMN
                    System.out.print(resultSet.getObject(i) + " |");
                }
                System.out.println();
            }


    }

}
