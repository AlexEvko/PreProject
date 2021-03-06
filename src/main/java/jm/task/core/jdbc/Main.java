package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        userService.dropUsersTable();
        userService.createUsersTable();

        userService.saveUser(user1);
        System.out.println("User "+user1.getName()+" добавлен");
        userService.saveUser(user2);
        System.out.println("User "+user2.getName()+" добавлен");
//        userService.saveUser(testName3, testLastName3, testAge3);
//        System.out.println("User "+testName3+" добавлен");
//        userService.saveUser(testName4, testLastName4, testAge4);
//        System.out.println("User "+testName4+" добавлен");
        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();
        // реализуйте алгоритм здесь
    }
    private static User user1 = new User("Ivan","Ivanov", (byte) 5);

    private static User user2 = new User("Van","Ivanov", (byte) 10);

    private static final UserService userService = new UserServiceImpl();
//    private static final String testName = "Ivan";
//    private static final String testLastName = "Ivanovich";
//    private static final byte testAge = 6;
//
//    private static final String testName2 = "Rick";
//    private static final String testLastName2 = "Trim";
//    private static final byte testAge2 = 17;
//
//    private static final String testName3 = "Kate";
//    private static final String testLastName3 = "Liss";
//    private static final byte testAge3 = 50;
//
//    private static final String testName4 = "Chara";
//    private static final String testLastName4 = "Stick";
//    private static final byte testAge4 = 64;
}
