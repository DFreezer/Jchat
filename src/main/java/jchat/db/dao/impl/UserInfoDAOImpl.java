package jchat.db.dao.impl;

import jchat.db.dao.UserInfoDAO;
import jchat.db.dataSet.UserInfo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInfoDAOImpl implements UserInfoDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserInfoDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(UserInfo obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public UserInfo read(Integer id) {
        return sessionFactory.getCurrentSession().get(UserInfo.class, id);
    }

    @Override
    public void update(UserInfo obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(UserInfo obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<UserInfo> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from UserInfo", UserInfo.class).getResultList();
    }
}
