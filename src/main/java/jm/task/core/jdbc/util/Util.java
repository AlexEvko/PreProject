package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;
import java.util.Properties;

public class Util {

    /*public static Connection getDBConnection() throws SQLException {
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
    }*/
    // то для jdbc открыть
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String DIALECT = "org.hibernate.dialect.PostgreSQL95Dialect";

    private static final SessionFactory concreteSessionFactory;
    static {
        try {
            Properties prop = new Properties();
            prop.setProperty("hibernate.connection.url",URL);
            prop.setProperty("hibernate.connection.username", USERNAME);
            prop.setProperty("hibernate.connection.password", PASSWORD);
            prop.setProperty("dialect", DIALECT);
            //prop.setProperty("show_sql", "true");

            prop.setProperty("hibernate.hbm2ddl.auto", "create");
            //ниже для 4 хибера
            /*Configuration configuration = new Configuration().addProperties(prop);
            SessionFactory sf = configuration.buildSessionFactory(
                    new StandardServiceRegistryBuilder()
                            .applySettings(prop)
                            .build());*/
            concreteSessionFactory = new org.hibernate.cfg.Configuration()
                    .addProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return concreteSessionFactory.openSession();
    }
}
