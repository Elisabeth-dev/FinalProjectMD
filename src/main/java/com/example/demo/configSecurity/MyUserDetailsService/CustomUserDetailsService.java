package com.example.demo.configSecurity.MyUserDetailsService;

import com.example.demo.entity.Account;
import com.example.demo.servic.AccountServiceRe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountServiceRe accountService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(account);
    }
}
