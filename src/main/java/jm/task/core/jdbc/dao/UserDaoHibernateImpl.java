package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    public void openCloseSession(String SQL){
        Transaction transaction = null;
        try (Session session = Util.getSession()){
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery(SQL);
            query.executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
    @Override
    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS users(Id SERIAL PRIMARY KEY, "
                    + "USER_NAME VARCHAR(200) NOT NULL, "
                    + "LAST_NAME VARCHAR(200) NOT NULL, "
                    + "AGE SMALLINT NOT NULL)";
        openCloseSession(SQL);
    }

    @Override
    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS users";
        openCloseSession(SQL);
    }

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;
        try (Session session = Util.getSession()){
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = Util.getSession()){
            transaction = session.beginTransaction();
            String SQL = "DELETE FROM User WHERE id = :id";
            session.createQuery(SQL)
                    .setParameter("id", id)
                    .executeUpdate();
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSession()){
            transaction = session.beginTransaction();
            users = session.createNativeQuery("SELECT*FROM users",User.class).list();
            transaction.commit();
        }
        catch (HibernateException e) {
            if(transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE users ";
        openCloseSession(SQL);
    }
}
