package com.example.rest;

import com.example.rest.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class Application {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = context.getBean("userService", UserService.class);


        List<User> users = userService.getUsers();
        System.out.println(users);

        User user = new User(3L, "James", "Brown", (byte) 23);
        userService.saveUser(user);

        System.out.println(users);

        user.setName("Thomas");
        user.setLastName("Black");
        userService.updateUser(user);

        System.out.println(users);

        userService.deleteUser(3L);

        System.out.println(users);

        System.out.println("Code: " + userService.getCode());
    }
}