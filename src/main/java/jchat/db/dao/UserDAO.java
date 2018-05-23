package jchat.db.dao;

import jchat.db.dataSet.*;

import java.util.List;

public interface UserDAO extends GenericDAO<User, Integer> {
    User getUserByName(String username);
    UserInfo getUserInfo(int idUser);
    List<UserContact> getUserContacts(int idUser);
    List<Message> getUserMessages(int idUser);
    List<Group> getUserGroups(int idUser);
    List<UserRole> getUserRoles(int idUser);
}
