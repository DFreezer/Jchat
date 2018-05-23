package jchat.db.dao;

import jchat.db.dataSet.*;

import java.util.List;
import java.util.Set;

public interface UserDAO extends GenericDAO<User, Integer> {
    User getUserByName(String username);
    UserInfo getUserInfo(int idUser);
    Set<UserContact> getUserContacts(int idUser);
    Set<Message> getUserMessages(int idUser);
    Set<Group> getUserGroups(int idUser);
    Set<UserRole> getUserRoles(int idUser);
}
