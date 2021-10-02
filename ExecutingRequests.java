package timetable;

import java.sql.*;
import java.util.*;

public class ExecutingRequests {

    static Statement statement;

    public ExecutingRequests() throws ClassNotFoundException, SQLException {
        try {
            //Связываемся с БД
            Connector connector = new Connector();
            statement = connector.connection();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Метод для решения Задания №1.
     * Вывести информацию о преподавателях, 
     * работающих в заданный день недели в заданной аудитории.
     */
    public void ThereAreClasses(String Day_of_the_week, String Office) throws SQLException {
        try {
            //SQL запрос для считывания таблицы с расписанием
            String sqlRequestTimetable = "SELECT * FROM timetable WHERE Day_of_the_week = '"
                    + Day_of_the_week + "'" + " AND Classroom = '" + Office + "'";
            ResultSet RS = this.statement.executeQuery(sqlRequestTimetable);
          

            //Массив для идентификаторов преподавателей
            ArrayList<String> teachers = new ArrayList<>();
            /*Запоняем массив идентицикаторами, удовлетворяющими запросом*/
            while (RS.next()) {
                teachers.add(RS.getString("ID_Teacher"));
            }
            
           //SQL запрос, считывающий таблицу преподавателей
            String sqlRequestTeachers = "SELECT * FROM teachers";
            RS = this.statement.executeQuery(sqlRequestTeachers);
            
            //Сравниваем идентификаторы в таблице с идентификаторами в массиве.
            int num = 0;
            while (RS.next()) {
                for (String i : teachers) {
                    if (RS.getString("ID").equals(i)) {
                        num++;
                        //выводим ФИО предподавателей, удовлетворябщих запросу 
                        System.out.println("\nРезультат №"
                                + num + "\n----------------");
                        System.out.println("Surname: "
                                + RS.getString("Surname"));
                        System.out.println("Name: "
                                + RS.getString("Name"));
                        System.out.println("Patronymic: "
                                + RS.getString("Patronymic"));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Метод для решения Задания №2.
     * Вывести информацию о преподавателях,
     * которые не ведут занятия в заданный день недели.
     */
    public void NoСlasses(String Day_of_the_week) throws SQLException {
        try {
            //SQL запрос для считывания таблицы с расписанием
            String sqlRequestTimetable = "SELECT * FROM timetable WHERE "
                    + "Day_of_the_week <> '" + Day_of_the_week + "'";
            ResultSet RS = this.statement.executeQuery(sqlRequestTimetable);

            //Массив для идентификаторов преподавателей
            ArrayList<String> teachers = new ArrayList<>();
            /*Запоняем массив идентицикаторами, удовлетворяющими запросом*/
            while (RS.next()) {
                teachers.add(RS.getString("ID_Teacher"));
            }
            
            //SQL запрос, считывающий таблицу преподавателей
            String sqlRequestTeachers = "SELECT * FROM teachers";
            RS = this.statement.executeQuery(sqlRequestTeachers);
            
            //Сравниваем идентификаторы в таблице с идентификаторами в массиве.
            int num = 0;
            while (RS.next()) {
                for (String i : teachers) {
                    if (RS.getString("ID").equals(i)) {
                        num++;
                        System.out.println("\nРезультат №"
                                + num + "\n----------------");
                        System.out.println("Surname: "
                                + RS.getString("Surname"));
                        System.out.println("Name: "
                                + RS.getString("Name"));
                        System.out.println("Patronymic: "
                                + RS.getString("Patronymic"));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Метод для решения Задания №3.
     * Перенести первые занятия заданных дней недели на последнее место.
     */
    public void FirstToLast(String day) throws SQLException {
        try {        
            //SQL запрос на удаление из таблицы первого занятия выбранного дня
            String sqlDelete = "DELETE FROM timetable WHERE ID IN (SELECT MIN(ID) "
                    + "FROM (SELECT * FROM timetable WHERE Day_of_the_week = '"
                    + day + "') AS ID_m)";
            
              //SQL запрос для выбора из таблицы первого занятия выбранного дня
            String sqlRequestTimetable = "SELECT * FROM timetable WHERE ID IN "
                    + "(SELECT MIN(ID) FROM timetable WHERE Day_of_the_week = '"
                    + day + "')";
            ResultSet RSTimetable = this.statement.executeQuery(sqlRequestTimetable);
            
            //массив для значений переносимой строки 
            ArrayList<String> StringArray = new ArrayList<>();

            //Записываем в массив всю строку
            if (RSTimetable.first()) {
                StringArray.add(String.valueOf(RSTimetable.getInt("ID")));
                StringArray.add(RSTimetable.getString("Day_of_the_week"));
                StringArray.add(String.valueOf(RSTimetable.getInt("ID_Subject")));
                StringArray.add(RSTimetable.getString("Classroom"));
                StringArray.add(String.valueOf(RSTimetable.getInt("ID_Teacher")));
                StringArray.add(RSTimetable.getString("Count_students"));
            }
            //Удаление строки
            this.statement.executeUpdate(sqlDelete);
            //Добавление новой строки с данными из массива
            String sqlInsertTimetable = "INSERT INTO timetable "
                    + "(ID, Day_of_the_week,ID_Subject, Classroom, ID_Teacher, Count_students) "
                    + "value (" + Integer.parseInt(StringArray.get(0)) + ",'"
                    + StringArray.get(1) + "',"
                    + Integer.parseInt(StringArray.get(2)) + ",'"
                    + StringArray.get(3) + "',"
                    + Integer.parseInt(StringArray.get(4)) + ",'"
                    + StringArray.get(5) + "')";
            this.statement.executeUpdate(sqlInsertTimetable);
            System.out.println("\nЗапрос успешно выполнен!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
