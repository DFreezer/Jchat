package jchat.db.dao;

import jchat.db.dataSet.UserContact;

import java.util.List;

public interface UserContactDAO extends GenericDAO<UserContact, Integer> {
    List<UserContact> findUserContacts(int idUser, String pattern);
}
