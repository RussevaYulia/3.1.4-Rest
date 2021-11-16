package com.example.rest;

import com.example.rest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class UserService {
    final String url = "http://91.241.64.178:7081/api/users";
    private String JSESSIONID;
    static String codeFull = "";

    private RestTemplate template = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();
    static HttpEntity<String> response;

    @Autowired
    public UserService(RestTemplate template) {
        this.template = template;
    }


    public List<User> getUsers() {
        ResponseEntity<List<User>> responseEntity = template.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = responseEntity.getBody();
        JSESSIONID = responseEntity.getHeaders().get("Set-Cookie").get(0);
        headers.add("Cookie", JSESSIONID);

        return users;
    }

    public void saveUser(User user) {
        ResponseEntity<String> responseEntity = template.postForEntity(url
                , new HttpEntity<>(user, headers)
                , String.class);

        System.out.println("New user was added to DB");
        codeFull += responseEntity.getBody();
    }

    public void updateUser(User user) {
        ResponseEntity<String> responseEntity = template.exchange(url
                , HttpMethod.PUT
                , new HttpEntity<>(user, headers)
                , String.class);
        System.out.println("User with ID " + user.getId() + " was updated");
        codeFull += responseEntity.getBody();
    }

    public void deleteUser(Long id) {
        ResponseEntity<String> responseEntity = template.exchange(url + "/" + id
                , HttpMethod.DELETE
                , new HttpEntity<>(headers)
                , String.class);
        System.out.println("User with ID " + id + " was deleted from DB");
        codeFull += responseEntity.getBody();
    }

    public String getCode() {
        return codeFull;
    }
}
