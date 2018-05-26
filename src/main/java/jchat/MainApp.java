package jchat;

import jchat.db.dataSet.Group;
import jchat.db.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring-config.xml");

        UserService userService = context.getBean("userService", UserService.class);

        List<Group> userGroups = userService.getUserGroups(3);

        for (Group group : userGroups) {
            System.out.println(group);
        }

        context.close();
    }

}
