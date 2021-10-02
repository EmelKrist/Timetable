package timetable;

import java.sql.*;

public class Connector {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL
            = "jdbc:mysql://127.0.0.1:3306/timetable?characterEncoding=utf8";

    static final String USER = "Kristina";
    static final String PASSWORD = "Emel@060620";

    public Connector() throws ClassNotFoundException, SQLException {
    }

    /**
     * Метод для подключения к БД.
     */
    public static Statement connection() throws ClassNotFoundException, SQLException {

        Connection connection = null;
        Statement statement = null;
        try {
            System.out.println("Regiatering JDBC driver...");
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("Creating database connection...");
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

            System.out.println("Executing statement...");
            statement = (Statement) connection.createStatement();
            System.out.println("The connection was made successfully!");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return statement;
    }

}
