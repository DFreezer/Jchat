package jchat.db.dao.impl;

import jchat.db.dao.GenericDAO;
import jchat.db.dao.UserRoleDAO;
import jchat.db.dataSet.UserRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserRoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(UserRole obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public UserRole read(Integer id) {
        return sessionFactory.getCurrentSession().get(UserRole.class, id);
    }

    @Override
    public void update(UserRole obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(UserRole obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<UserRole> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from UserRole", UserRole.class).getResultList();
    }
}
