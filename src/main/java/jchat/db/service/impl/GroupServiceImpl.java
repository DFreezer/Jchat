package jchat.db.service.impl;

import jchat.db.dao.GroupDAO;
import jchat.db.dao.GroupMessageDAO;
import jchat.db.dataSet.Group;
import jchat.db.dataSet.GroupMessage;
import jchat.db.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("groupService")
@Transactional(rollbackFor = Exception.class)
public class GroupServiceImpl implements GroupService {

    private final GroupMessageDAO groupMessageDAO;
    private final GroupDAO groupDAO;

    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO, GroupMessageDAO groupMessageDAO) {
        this.groupDAO = groupDAO;
        this.groupMessageDAO = groupMessageDAO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createGroup(Group group) {
        return groupDAO.create(group);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Group readGroup(int id) {
        return groupDAO.read(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGroup(Group group) {
        groupDAO.update(group);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGroup(Group group) {
        groupDAO.delete(group);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createGroupMessage(GroupMessage groupMessage) {
        return groupMessageDAO.create(groupMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void readGroupMessage(int idGroupMessage) {
        groupMessageDAO.read(idGroupMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGroupMessage(GroupMessage groupMessage) {
        groupMessageDAO.update(groupMessage);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteGroupMessage(GroupMessage groupMessage) {
        groupMessageDAO.delete(groupMessage);
    }
}
