package jchat;

import jchat.db.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring-config.xml");

        UserService userService = context.getBean("userService", UserService.class);

        context.close();
    }

}
