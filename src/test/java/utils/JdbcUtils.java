package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;

    private static Statement establishConnection(){
        try{
            connection = DriverManager.getConnection(
                    getProp("connection_string"),
                    getProp("username"),
                    getProp("password"));
            statement = connection
                    .createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        }catch (SQLException ex){
            throw new RuntimeException("Could not connect to database");
        }
        return statement;
    }

    public static ResultSet query(String query){

        statement = establishConnection();
        try{
            resultSet = statement.executeQuery(query);

        }catch (SQLException ex){
            throw new RuntimeException("Failed running query",ex);
        }

        return resultSet;
    }

    private static String getProp(String key){
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream( new File("/Users/codefish/IdeaProjects/JDBC_batch17/src/test/resources/database.properties")));
        }catch (IOException ex){
            throw new RuntimeException("Could not find the properties file");
        }
        return properties.getProperty(key);
    }


    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
