package jchat.db.dao.impl;

import jchat.db.dao.UserDAO;
import jchat.db.dataSet.*;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        Query<UserInfo> query = sessionFactory.getCurrentSession().createQuery("from UserInfo where user.idUser =:idUser", UserInfo.class);
        query.setParameter("idUser", idUser);
        return query.uniqueResult();
    }

    @Override
    public List<UserContact> getUserContacts(int idUser) {
        Query<UserContact> query = sessionFactory
                .getCurrentSession()
                .createQuery("from UserContact userContact join fetch userContact.user join fetch userContact.contact where userContact.user.idUser =:idUser", UserContact.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }

    @Override
    public List<Message> getUserMessages(int idUser) {
        Query<Message> query = sessionFactory
                .getCurrentSession()
                .createQuery("from Message where user.idUser =:idUser", Message.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }

    @Override
    public List<Group> getUserGroups(int idUser) {
        Query<Group> query = sessionFactory
                .getCurrentSession()
                .createQuery("from Group groups join fetch groups.groupUsers groupUsers join fetch groupUsers.user user where user.idUser = :idUser", Group.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }

    @Override
    public List<UserRole> getUserRoles(int idUser) {
        Query<UserRole> query = sessionFactory
                .getCurrentSession()
                .createQuery("from UserRole where user.idUser =:idUser", UserRole.class);
        query.setParameter("idUser", idUser);
        return query.getResultList();
    }
}
