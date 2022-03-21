package timetable;

import java.sql.SQLException;

public class Timetable {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            //Запускаем программу.
            Start launch = new Start();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
