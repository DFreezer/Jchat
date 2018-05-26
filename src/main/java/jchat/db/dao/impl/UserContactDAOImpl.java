package jchat.db.dao.impl;

import jchat.db.dao.UserContactDAO;
import jchat.db.dataSet.UserContact;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserContactDAOImpl implements UserContactDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserContactDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer create(UserContact obj) {
        return (Integer) sessionFactory.getCurrentSession().save(obj);
    }

    @Override
    public UserContact read(Integer id) {
        return sessionFactory.getCurrentSession().get(UserContact.class, id);
    }

    @Override
    public void update(UserContact obj) {
        sessionFactory.getCurrentSession().update(obj);
    }

    @Override
    public void delete(UserContact obj) {
        sessionFactory.getCurrentSession().delete(obj);
    }

    @Override
    public List<UserContact> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from UserContact", UserContact.class).getResultList();
    }

    @Override
    public List<UserContact> findUserContacts(int idUser, String pattern) {
        Query<UserContact> query = sessionFactory.getCurrentSession().createQuery("from UserContact userContact join fetch userContact.contact contact join fetch userContact.user user where user.idUser = :idUser and contact.username like :pattern", UserContact.class);
        query.setParameter("idUser", idUser);
        query.setParameter("pattern", "%" + pattern + "%");
        return query.getResultList();
    }
}
