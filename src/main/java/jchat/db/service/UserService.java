package jchat.db.service;

import jchat.db.dataSet.*;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public interface UserService {
    int createUser(String username, String password, String email, Date regDate, boolean status);
    User readUser(int id);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserByName(String username);
    UserInfo getUserInfo(int idUser);
    Set<UserContact> getUserContacts(int idUser);
    Set<Message> getUserMessages(int idUser);
    Set<Group> getUserGroups(int idUser);
    Set<UserRole> getUserRoles(int idUser);
    int createUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
    void deleteUserInfo(UserInfo userInfo);
    int createUserContact(UserContact userContact);
    int createMessage(Message message);
    Message readMessage(int idMessage);
    void updateMessage(Message message);
    void deleteMessage(Message message);
}
