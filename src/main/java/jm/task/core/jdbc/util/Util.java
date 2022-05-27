package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
//    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String URL = "jdbc:mysql://localhost:3306/jdbc";
//    private static final String USER_NAME = "user";
//    private static final String PASSWORD = "12345";
//
//    public static Connection getConnection(){
//        Connection connection = null;
//
//        try {
//            Class.forName(DRIVER);
//            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//
//        return connection;
//    }
//
//}

    public static Connection getDBConnection() throws SQLException {
        Connection dbConnection = null;
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbConnection;
    }
    // реализуйте настройку соединения с БД
}
