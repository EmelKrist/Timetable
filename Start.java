package timetable;

import java.sql.SQLException;
import java.util.Scanner;

public class Start {

    static ExecutingRequests request;
    static String Day;

    public Start() throws ClassNotFoundException, SQLException {
        try {
            request = new ExecutingRequests();
            MainMenu(); 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Метод для взаимодействия с приложением.
     * 
     */
    public static void MainMenu() throws SQLException {
        try{
        Scanner in = new Scanner(System.in);
        do {
            System.out.println("\nМеню выбора:"
                    + "\n1.Вывести информацию о преподавателях, работающих "
                    + "\nв заданный день недели в заданной аудитории.\n"
                    + "\n2.Вывести информацию о преподавателях, которые "
                    + "\nне ведут занятия в заданный день недели.\n"
                    + "\n3.Перенести первые занятия заданных"
                    + "\nдней недели на последнее место.\n"
                    + "\n4.Выйти из приложения.\n");
            System.out.print("Выберите пункт: ");
            String dayOfTheWeek;
            int Choice = in.nextInt();
            switch (Choice) {
                case 1:
                    dayOfTheWeek = Weekdays();
                    String office = Classroom();
                    System.out.println("\nРезультаты выполнения запроса:");
                    request.ThereAreClasses(dayOfTheWeek, office);
                    break;
                case 2:
                    dayOfTheWeek = Weekdays();
                    System.out.println("\nРезультаты выполнения запроса:");
                    request.NoСlasses(dayOfTheWeek);
                    break;
                case 3:
                    dayOfTheWeek = Weekdays();
                    request.FirstToLast(dayOfTheWeek);
                    break;
                case 4:
                    System.out.println("Работа завершена.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Такого пункта меню не существует!\n");
            }
        } while (true);
        
           } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     /**
     * Метод для выбора дня недели..
     * 
     */
    public static String Weekdays() throws SQLException {
        try{
        Scanner in = new Scanner(System.in);
        System.out.println("\nДоступные дни недели:"
                + "\n1.Понедельник"
                + "\n2.Вторник"
                + "\n3.Среда"
                + "\n4.Четверг"
                + "\n5.Пятница\n");
        System.out.print("Выберите пункт: ");
        String Choice = in.nextLine();
        switch (Choice) {
            case "1":
                Day = "Monday";
                break;
            case "2":
                Day = "Tuesday";
                break;
            case "3":
                Day = "Wednesday";
                break;
            case "4":
                Day = "Thursday";
                break;
            case "5":
                Day = "Friday";
                break;
            default:
                System.out.println("Такого пункта выбора не существует!\n");
                MainMenu();
        }
        
           } catch (SQLException e) {
            e.printStackTrace();
        }        
        return Day;
    }
    
     /**
     * Метод для ввода номера кабинета.
     * 
     */
    public static String Classroom() {
        Scanner in = new Scanner(System.in);
        System.out.print("Укажите номер кабинета: ");
        String office = in.nextLine();
        return office;
    }

}
