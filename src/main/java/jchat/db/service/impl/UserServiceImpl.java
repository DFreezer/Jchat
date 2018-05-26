package jchat.db.service.impl;

import jchat.db.dao.*;
import jchat.db.dataSet.*;
import jchat.db.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleDAO userRoleDAO;
    private final RoleDAO roleDAO;
    private final UserInfoDAO userInfoDAO;
    private final UserContactDAO userContactDAO;
    private final MessageDAO messageDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder, UserRoleDAO userRoleDAO, RoleDAO roleDAO, UserInfoDAO userInfoDAO, UserContactDAO userContactDAO, MessageDAO messageDAO) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.userRoleDAO = userRoleDAO;
        this.roleDAO = roleDAO;
        this.userInfoDAO = userInfoDAO;
        this.userContactDAO = userContactDAO;
        this.messageDAO = messageDAO;
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByName(username);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(username);
            userBuilder.password(user.getPassword());
        } else {
            throw new UsernameNotFoundException("User " + username + " not found!");
        }
        List<GrantedAuthority> grantedAuthorities = userRoleBuilder(user.getRoles());
        return authenticatedUser(user, grantedAuthorities);
    }

    private org.springframework.security.core.userdetails.User authenticatedUser(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, true, authorities);
    }

    private List<GrantedAuthority> userRoleBuilder(Set<UserRole> userRoleSet) {
        Set<GrantedAuthority> authorities = new HashSet<>();

        for (UserRole userRole : userRoleSet) {
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getType()));
        }

        return new ArrayList<>(authorities);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createUser(String username, String password, String email, Date regDate, boolean status) {
        User user = new User(username, passwordEncoder.encode(password), email, regDate, status);
        Integer userId = userDAO.create(user);
        userRoleDAO.create(new UserRole(user, roleDAO.read(1)));
        return userId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User readUser(int id) {
        return userDAO.read(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User getUserByName(String username) {
        return userDAO.getUserByName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserInfo getUserInfo(int idUser) {
        return userDAO.getUserInfo(idUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserContact> getUserContacts(int idUser) {
        return userDAO.getUserContacts(idUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Message> getUserMessages(int idUser) {
        return userDAO.getUserMessages(idUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Group> getUserGroups(int idUser) {
        return userDAO.getUserGroups(idUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserRole> getUserRoles(int idUser) {
        return userDAO.getUserRoles(idUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createUserInfo(UserInfo userInfo) {
        return userInfoDAO.create(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserInfo(UserInfo userInfo) {
        userInfoDAO.update(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserInfo(UserInfo userInfo) {
        userInfoDAO.delete(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createUserContact(UserContact userContact) {
        return userContactDAO.create(userContact);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createMessage(Message message) {
        return messageDAO.create(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Message readMessage(int idMessage) {
        return messageDAO.read(idMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMessage(Message message) {
        messageDAO.update(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMessage(Message message) {
        messageDAO.delete(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMessageById(int idMessage) {
        deleteMessage(readMessage(idMessage));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateMessageBody(int idMessage, String body) {
        Message message = readMessage(idMessage);
        message.setBody(body);
        updateMessage(message);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<UserContact> findUserContacts(int idUser, String pattern) {
        return userContactDAO.findUserContacts(idUser, pattern);
    }
}
