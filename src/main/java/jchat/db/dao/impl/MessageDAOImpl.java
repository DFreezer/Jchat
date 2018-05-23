package jchat.db.dao.impl;

import jchat.db.dao.GenericDAO;
import jchat.db.dao.MessageDAO;
import jchat.db.dataSet.Message;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageDAOImpl implements MessageDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public MessageDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(Message obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public Message read(Integer id) {
        return sessionFactory.getCurrentSession().get(Message.class, id);
    }

    @Override
    public void update(Message obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(Message obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<Message> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Message", Message.class).getResultList();
    }
}
