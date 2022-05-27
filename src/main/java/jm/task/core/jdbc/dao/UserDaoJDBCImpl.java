package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String createUsersTable = "CREATE TABLE IF NOT EXISTS users(Id SERIAL PRIMARY KEY, "
                + "USER_NAME VARCHAR(200) NOT NULL, "
                + "LAST_NAME VARCHAR(200) NOT NULL, "
                + "AGE SMALLINT NOT NULL)";
        try (Connection connection = Util.getDBConnection();PreparedStatement preparedStatement =
                connection.prepareStatement(createUsersTable)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String DropSQL = "DROP TABLE IF EXISTS users ";
        //autocloseable ----------
        try (Connection connection = Util.getDBConnection();PreparedStatement preparedStatement =
                connection.prepareStatement(DropSQL)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveSQL =  "INSERT INTO users(USER_NAME, LAST_NAME, AGE)VALUES(?, ?, ?)";
        try (Connection connection = Util.getDBConnection();PreparedStatement preparedStatement =
                connection.prepareStatement(saveSQL)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String RemoveSQL = "DELETE FROM users WHERE Id = id";
        try (Connection connection = Util.getDBConnection();PreparedStatement preparedStatement =
                connection.prepareStatement(RemoveSQL)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List User = new ArrayList<>();
        String SelectSQL = "SELECT*FROM users";
        try (Connection connection = Util.getDBConnection();PreparedStatement preparedStatement =
                connection.prepareStatement(SelectSQL)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Long userId = rs.getLong("Id");

                String name = rs.getString("USER_NAME");
                String lastName = rs.getString("LAST_NAME");
                Byte age = rs.getByte("AGE");
                User  user = new User(name,lastName,age);
                user.setId(userId);
                User.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return User;
    }

    public void cleanUsersTable() {
        String cleanSQL = "TRUNCATE TABLE users ";
        try (Connection connection = Util.getDBConnection();PreparedStatement preparedStatement =
                connection.prepareStatement(cleanSQL)){
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
