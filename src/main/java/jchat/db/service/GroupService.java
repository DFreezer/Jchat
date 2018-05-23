package jchat.db.service;

import jchat.db.dataSet.Group;
import jchat.db.dataSet.GroupMessage;

public interface GroupService {
    int createGroup(Group group);
    Group readGroup(int id);
    void updateGroup(Group group);
    void deleteGroup(Group group);
    int createGroupMessage(GroupMessage groupMessage);
    void readGroupMessage(int idGroupMessage);
    void updateGroupMessage(GroupMessage groupMessage);
    void deleteGroupMessage(GroupMessage groupMessage);
}