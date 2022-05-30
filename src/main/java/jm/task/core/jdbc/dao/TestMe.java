package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestMe {

    public static void main(String[] args) {
        users.add(new User("Abc1", "123", (byte) 22));
        users.add(new User("Abc12", "123", (byte) 22));
        users.add(new User("Abc123", "123", (byte) 22));
        users.add(new User("Abc1234", "123", (byte) 22));
        users.add(new User("Abc12345", "123", (byte) 22));
        loopWithFori(users);
    }

    public static List<User> users = new ArrayList<>();
//            Arrays.asList(new User("Abc", "123", (byte) 22),
//                                                   new User("Sanya", "Evko", (byte) 22),
//                                                   new User("Vlad1", "Nedobugin", (byte) 23),
//                                                   new User("Vlad12", "Nedobugin", (byte) 24),
//                                                   new User("Vlad122", "Nedobugin", (byte) 25));

    public static void loopWithFori(List<User> users) {
        System.out.println("Initial size = " + users.size());
        for (int i = 0; i < users.size(); i++) {
            System.out.println("Iteration number = " + (i + 1) );
            System.out.println("Index = " + i);
            System.out.println("Size = " + users.size());
            System.out.println(users.get(i).getName());
            if (i == 1 || i == 3) {
                users.remove(3);
                System.out.println("Size = " + users.size());
            }
        }
    }

    public static void loopWithForEach(List<User> users) {
        for (User user: users) {
            System.out.println(user.getName());
            if (user.getName().equals("Abc1")) {
                users.remove(3);
            }
        }
        System.out.println("All OK");
    }
}
