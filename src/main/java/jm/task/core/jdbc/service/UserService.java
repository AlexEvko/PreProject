package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    void createUsersTable() throws SQLException;

    void dropUsersTable();

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();
}
