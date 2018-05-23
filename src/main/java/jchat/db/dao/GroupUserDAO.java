package jchat.db.dao;

import jchat.db.dataSet.GroupUser;

import java.util.List;

public interface GroupUserDAO extends GenericDAO<GroupUser, Integer> {
    List<GroupUser> getGroupUsers(int idGroup);
}
