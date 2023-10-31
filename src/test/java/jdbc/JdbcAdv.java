package jdbc;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import utils.JdbcUtils;

import java.sql.*;
import java.util.*;

public class JdbcAdv {
    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.
                getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student",
                        "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();

//       List<Map<String, Object> > empList = new ArrayList<>();

        Map<String, Object> rowMap = new HashMap<>();
        List<Map<String, Object>> emplList = new ArrayList<>();

        while (resultSet.next()){

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i),resultSet.getObject(i));
            }
            emplList.add(rowMap);
        }

//        System.out.println(emplList.get(0).get("FIRST_NAME"));
//        System.out.println(emplList.get(0));




    }

    @Test
    public void testSalary() throws SQLException {  //VALIDATE THE Steven King  HAS SALARY OF 24000
        Connection connection = DriverManager.
                getConnection("jdbc:oracle:thin:@database-1.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                        "student",
                        "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();

//       List<Map<String, Object> > empList = new ArrayList<>();

        Map<String, Object> rowMap = new HashMap<>();
        List<Map<String, Object>> emplList = new ArrayList<>();

        while (resultSet.next()){

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i),resultSet.getObject(i));
            }
            emplList.add(rowMap);
        }
        for (int i = 0 ; i < emplList.size(); i++){
            if(emplList.get(i).get("FIRST_NAME").equals("Steven")){
//                Assert.assertEquals(24000,emplList.get(i).get("SALARY"));
                Assert.assertEquals(24000,Integer.parseInt(emplList.get(i).get("SALARY").toString()));
            }
        }
    }

    @Test
    public void testSalary1()throws SQLException{
        ResultSet resultSet = JdbcUtils.query("select * from employees");
        ResultSetMetaData metaData = resultSet.getMetaData();

        Map<String, Object> rowMap = new HashMap<>();
        List<Map<String, Object>> emplList = new ArrayList<>();

        while (resultSet.next()){

            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i),resultSet.getObject(i));
            }
            emplList.add(rowMap);
        }
        for (int i = 0 ; i < emplList.size(); i++){
            if(emplList.get(i).get("FIRST_NAME").equals("Steven")){
//                Assert.assertEquals(24000,emplList.get(i).get("SALARY"));
                Assert.assertEquals(24000,Integer.parseInt(emplList.get(i).get("SALARY").toString()));
            }
        }
    }

    @AfterClass
    public static void tearDown(){
        JdbcUtils.closeConnection();
        System.out.println("the tear down");
    }
}
