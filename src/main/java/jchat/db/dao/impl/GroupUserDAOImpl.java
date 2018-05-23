package jchat.db.dao.impl;

import jchat.db.dao.GroupUserDAO;
import jchat.db.dataSet.GroupUser;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupUserDAOImpl implements GroupUserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public GroupUserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(GroupUser obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public GroupUser read(Integer id) {
        return sessionFactory.getCurrentSession().get(GroupUser.class, id);
    }

    @Override
    public void update(GroupUser obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(GroupUser obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<GroupUser> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from GroupUser", GroupUser.class).getResultList();
    }

    @Override
    public List<GroupUser> getGroupUsers(int idGroup) {
        Query<GroupUser> query = sessionFactory.getCurrentSession().createQuery("from GroupUser groupUser join fetch groupUser.user where groupUser.group.idGroup = :idGroup", GroupUser.class);
        query.setParameter("idGroup", idGroup);
        return query.getResultList();
    }
}
