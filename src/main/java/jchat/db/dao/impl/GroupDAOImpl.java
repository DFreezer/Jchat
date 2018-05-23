package jchat.db.dao.impl;

import jchat.db.dao.GenericDAO;
import jchat.db.dao.GroupDAO;
import jchat.db.dataSet.Group;
import jchat.db.dataSet.GroupMessage;
import jchat.db.dataSet.GroupUser;
import jchat.db.dataSet.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupDAOImpl implements GroupDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public Integer create(Group obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public Group read(Integer id) {
        return sessionFactory.getCurrentSession().get(Group.class, id);
    }

    @Override
    public void update(Group obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(Group obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Group> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Group", Group.class).getResultList();
    }
}
