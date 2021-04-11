package com.example.demo.entity;

import com.example.demo.mylists.MyList;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "my_list_ac")
@Data
public class MyListAc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myListAcId;

    private String nameAc;

    @JsonBackReference
    @OneToMany( fetch=FetchType.LAZY, mappedBy = "myListAc", cascade = CascadeType.ALL)
    @Column(name = "bankCard")
    private List<BankCard> bankCard;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "account_Id")
    private Account account;



}
