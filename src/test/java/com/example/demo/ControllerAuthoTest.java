package com.example.demo;

import com.example.demo.configSecurity.AuthRequest;
import com.example.demo.configSecurity.AuthResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerAuthoTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void whenGetUser_thenCorrect() {

        AuthResponse authResponse = getAuthHeaderForUser("liza", "liza");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + authResponse.getJwtToken());
        ResponseEntity<String> response = restTemplate.exchange("/user", HttpMethod.GET, new HttpEntity<>(headers), String.class);

        assertTrue(response.getBody().equals("User"));

    }

    private AuthResponse getAuthHeaderForUser(String login, String password) {

        AuthRequest authRequest = new AuthRequest();
        authRequest.setLogin(login);
        authRequest.setPassword(password);
        AuthResponse authResponse = restTemplate.postForObject("/authenticate", authRequest, AuthResponse.class);

        return authResponse;
    }

}

