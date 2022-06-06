package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String SQL = "CREATE TABLE IF NOT EXISTS users(Id SERIAL PRIMARY KEY, "
                    + "USER_NAME VARCHAR(200) NOT NULL, "
                    + "LAST_NAME VARCHAR(200) NOT NULL, "
                    + "AGE SMALLINT NOT NULL)";

            Query query = session.createNativeQuery(SQL);
            query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String SQL = "DROP TABLE IF EXISTS users";

            Query query = session.createNativeQuery(SQL);
            query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(User user) {
        Session session = Util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            List<User> users = session.createNativeQuery("SELECT*FROM users",User.class).list();
            transaction.commit();
            return users;
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String SQL = "TRUNCATE TABLE users ";

            Query query = session.createNativeQuery(SQL);
            query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
