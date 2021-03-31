package com.example.demo.servic;


import com.example.demo.entity.Account;
import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.RoleEntityRepository;
import com.example.demo.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceRe{

    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account saveUser(Account account) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        account.setRoleEntity(userRole);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return userEntityRepository.save(account);
    }

    public Account findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    public Account findByLoginAndPassword(String login, String password) {
        Account account = findByLogin(login);
        if (account != null) {
            if (passwordEncoder.matches(password, account.getPassword())) {
                return account;
            }
        }
        return null;
    }
}
