package com.example.demo.controller;

import com.example.demo.configSecurity.AuthRequest;
import com.example.demo.configSecurity.AuthResponse;

import com.example.demo.configSecurity.JwtProvider;
import com.example.demo.configSecurity.RegistrationRequest;
import com.example.demo.entity.Account;
import com.example.demo.servic.AccountServiceRe;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

@RestController
public class ControllerAuthorization {

    @Autowired
    private AccountServiceRe accountService;
    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        Account account = new Account();
        account.setPassword(registrationRequest.getPassword());
        account.setLogin(registrationRequest.getLogin());
        accountService.saveUser(account);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        Account account = accountService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(account.getLogin());
        return new AuthResponse(token);
    }

    @GetMapping("/user/get")
    public String getUser() {
        return "USER";
    }

}
