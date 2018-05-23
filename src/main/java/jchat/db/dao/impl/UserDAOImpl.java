package jchat.db.dao.impl;

import jchat.db.dao.UserDAO;
import jchat.db.dataSet.*;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Integer create(User obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public User read(Integer id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public void update(User obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(User obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<User> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserByName(String username) {
        Query<User> query = sessionFactory.getCurrentSession().createQuery("from User where username = :username", User.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public UserInfo getUserInfo(int idUser) {
        return read(idUser).getUserInfo();
    }

    @Override
    public Set<UserContact> getUserContacts(int idUser) {
        return read(idUser).getUserContacts();
    }

    @Override
    public Set<Message> getUserMessages(int idUser) {
        return read(idUser).getMessages();
    }

    @Override
    public Set<Group> getUserGroups(int idUser) {
        return read(idUser).getGroups();
    }

    @Override
    public Set<UserRole> getUserRoles(int idUser) {
        return read(idUser).getRoles();
    }
}
