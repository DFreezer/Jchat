package jchat;

import jchat.db.dataSet.User;
import jchat.db.dataSet.UserContact;
import jchat.db.dataSet.UserInfo;
import jchat.db.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Date;
import java.util.Calendar;
import java.util.Set;

public class MainApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");

        UserService userService = context.getBean("userService", UserService.class);

        int userId = userService.createUser("frog15", "qwerty12345", "frog15@gmail.com", new Date(Calendar.getInstance().getTimeInMillis()), false);

        User user = userService.readUser(userId);

        System.out.println(user);

        userService.createUserInfo(new UserInfo("John", "Smith", user));

        System.out.println(userService.getUserInfo(userId));

        userService.createUserContact(new UserContact(user, userService.getUserByName("cooper39")));

        Set<UserContact> userContacts = userService.getUserContacts(userId);
        for (UserContact us : userContacts) {
            System.out.println(us);
        }

        context.close();
    }

}
