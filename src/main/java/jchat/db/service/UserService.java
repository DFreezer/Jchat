package jchat.db.service;

import jchat.db.dataSet.*;

import java.sql.Date;
import java.util.List;

public interface UserService {
    int createUser(String username, String password, String email, Date regDate, boolean status);
    User readUser(int id);
    void updateUser(User user);
    void deleteUser(User user);
    User getUserByName(String username);
    UserInfo getUserInfo(int idUser);
    List<UserContact> getUserContacts(int idUser);
    List<Message> getUserMessages(int idUser);
    List<Group> getUserGroups(int idUser);
    List<UserRole> getUserRoles(int idUser);
    int createUserInfo(UserInfo userInfo);
    void updateUserInfo(UserInfo userInfo);
    void deleteUserInfo(UserInfo userInfo);
    int createUserContact(UserContact userContact);
    int createMessage(Message message);
    Message readMessage(int idMessage);
    void updateMessage(Message message);
    void deleteMessage(Message message);
    void deleteMessageById(int idMessage);
}
