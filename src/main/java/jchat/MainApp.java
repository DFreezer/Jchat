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

        context.close();
    }

}
