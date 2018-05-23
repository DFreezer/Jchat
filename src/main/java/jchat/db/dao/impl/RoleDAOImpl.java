package jchat.db.dao.impl;

import jchat.db.dao.GenericDAO;
import jchat.db.dao.RoleDAO;
import jchat.db.dataSet.Role;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Role obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public Role read(Integer id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Override
    public void update(Role obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(Role obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Role> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Role", Role.class).getResultList();
    }
}
