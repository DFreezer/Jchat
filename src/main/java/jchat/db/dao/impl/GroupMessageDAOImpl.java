package jchat.db.dao.impl;

import jchat.db.dao.GroupMessageDAO;
import jchat.db.dataSet.GroupMessage;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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

    @Override
    public List<GroupMessage> getGroupMessages(int idGroup) {
        Query<GroupMessage> query = sessionFactory.getCurrentSession().createQuery("from GroupMessage groupMessage join fetch groupMessage.message.sender where groupMessage.group.idGroup = :idGroup order by groupMessage.idGroupMessage asc", GroupMessage.class);
        query.setParameter("idGroup", idGroup);
        return query.getResultList();
    }

    @Override
    public List<GroupMessage> getGroupMessagesByUser(String username, int idGroup) {
        Query<GroupMessage> query = sessionFactory.getCurrentSession()
                .createQuery("from GroupMessage groupMessage join fetch groupMessage.message.sender user join fetch groupMessage.group where user.username = :username and groupMessage.group.idGroup = :idGroup", GroupMessage.class);
        query.setParameter("username", username);
        query.setParameter("idGroup", idGroup);
        return query.getResultList();
    }
}
