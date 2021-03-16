package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "bankCard")
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bankCardId;

    private String nameCard;

    private int cardNumber;

    @ManyToOne
    @JoinColumn(name = "myListAc_id")
    private MyListAc myListAc;


}
