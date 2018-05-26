package jchat.db.dao;

import jchat.db.dataSet.GroupMessage;

import java.util.List;

public interface GroupMessageDAO extends GenericDAO<GroupMessage, Integer> {
    List<GroupMessage> getGroupMessages(int idGroup);
    List<GroupMessage> getGroupMessagesByUser(String username, int idGroup);
}
