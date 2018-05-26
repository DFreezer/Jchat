package jchat.controller;

import jchat.db.dataSet.*;
import jchat.db.service.GroupService;
import jchat.db.service.UserService;
import jchat.dto.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value = "/jchat", method = {RequestMethod.GET, RequestMethod.POST})
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public ChatController(GroupService groupService, UserService userService, SimpMessagingTemplate messagingTemplate) {
        this.groupService = groupService;
        this.userService = userService;
        this.messagingTemplate = messagingTemplate;
    }

    @GetMapping(value = "/main")
    public String mainPage(@RequestParam(value = "groupId", required = false) String groupId, ModelMap modelMap, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        modelMap.addAttribute("groupList", userService.getUserGroups(user.getIdUser()));
        List<UserContact> userContacts = userService.getUserContacts(user.getIdUser());
        if (userContacts != null) {
            modelMap.addAttribute("userContacts", userContacts);
            modelMap.addAttribute("username", principal.getName());
        }
        return "main";
    }

    @GetMapping(value = "/groups/{groupId}")
    public String messagesMenu(@PathVariable("groupId") String groupId, ModelMap modelMap, Principal principal) {
        if (groupId != null) {
            int idGroup = Integer.parseInt(groupId);
            List<GroupMessage> allGroupMessages = groupService.getGroupMessages(idGroup);
            List<GroupUser> groupUserList = groupService.getGroupUsers(idGroup);
            int groupUsersCount = groupUserList.size();
            if (allGroupMessages != null) {
                modelMap.addAttribute("groupMessages", allGroupMessages);
                modelMap.addAttribute("usersCount", groupUsersCount);
                modelMap.addAttribute("groupUsers", groupUserList);
                modelMap.addAttribute("username", principal.getName());
            }
        }
        return "messagesMenu";
    }

    @MessageMapping("/rooms/{roomId}")
    public void messageToRoom(@DestinationVariable String roomId, String message, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        Group group = groupService.readGroup(Integer.parseInt(roomId));
        Date sendDate = new Date(Calendar.getInstance().getTimeInMillis());
        int messageId = groupService.createGroupMessage(new GroupMessage(new Message(message, sendDate, user), group));
        messagingTemplate.convertAndSend("/topic/group/" + roomId, new MessageDTO(messageId, principal.getName(), message, sendDate.toLocalDate().toString()));
    }

    @GetMapping(value = "/message")
    public String getMessage() {
        return "message";
    }

    @PostMapping(value = "/create-group")
    @ResponseBody
    public Group createGroup(@RequestParam("groupName") String groupName, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        int idGroup = groupService.createGroup(new Group(groupName, new Date(Calendar.getInstance().getTimeInMillis()), user));
        return groupService.readGroup(idGroup);
    }

    @GetMapping(value = "/group-item")
    public String groupItem() {
        return "groupItem";
    }

    @PostMapping(value = "/group-edit")
    @ResponseBody
    public void editGroup(@RequestParam("groupId") int groupId, @RequestParam("groupName") String groupName) {
        Group group = groupService.readGroup(groupId);
        group.setName(groupName);
        groupService.updateGroup(group);
    }

    @PostMapping(value = "/delete-message")
    @ResponseBody
    public void deleteMessage(@RequestParam("msgId") int messageId) {
        userService.deleteMessageById(messageId);
    }

    @PostMapping(value = "/edit-message")
    @ResponseBody
    public void editMessage(@RequestParam("msgId") int messageId, @RequestParam("msgBody") String body) {
        userService.updateMessageBody(messageId, body);
    }

    @PostMapping(value = "/search-messages")
    @ResponseBody
    public List<GroupMessage> searchMessages(@RequestParam("username") String username, @RequestParam("idGroup") int idGroup) {
        return groupService.getGroupMessagesByUser(username, idGroup);
    }

    @PostMapping(value = "/search-contacts")
    @ResponseBody
    public List<UserContact> searchContacts(@RequestParam("term") String pattern, Principal principal) {
        User user = userService.getUserByName(principal.getName());
        return userService.findUserContacts(user.getIdUser(), pattern);
    }
}