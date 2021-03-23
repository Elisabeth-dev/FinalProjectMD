package com.example.demo.servic;

import com.example.demo.DAO.AccountEntityRepository;
import com.example.demo.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceRe implements UserDetailsService {

    @Autowired
    private AccountEntityRepository accountEntityRepository;


    @Override
    public UserDetails loadUserByUsername(String accountLogin) throws UsernameNotFoundException {
        Account account = accountEntityRepository.findByLogin(accountLogin);
        if(account == null){
            throw new UsernameNotFoundException("Unknown user: "+accountLogin);
        }

        UserDetails user = User.builder()
                .username(account.getLogin())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
        return user;
    }
}
