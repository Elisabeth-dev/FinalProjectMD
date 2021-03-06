package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user_table")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_Id;

    private String login;

    private String password;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL)
    @Column(name = "account_id")
    private List<MyListAc> myListAc;


}
