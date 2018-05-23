package jchat.db.dao.impl;

import jchat.db.dao.GroupMessageDAO;
import jchat.db.dataSet.GroupMessage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupMessageDAOImpl implements GroupMessageDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupMessageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(GroupMessage obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public GroupMessage read(Integer id) {
        return sessionFactory.getCurrentSession().get(GroupMessage.class, id);
    }

    @Override
    public void update(GroupMessage obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(GroupMessage obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<GroupMessage> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from GroupMessage", GroupMessage.class).getResultList();
    }
}
