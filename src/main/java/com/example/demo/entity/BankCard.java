package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bankCard")
@Data
public class BankCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private Long bankCardId;

    private String nameCard;

    private int cardNumber;

    @JsonBackReference
    @ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "myListAc_id")
    private MyListAc myListAc;


    public BankCard() {
    }

    public BankCard(String nameCard, int cardNumber, MyListAc myListAc) {
        this.nameCard = nameCard;
        this.cardNumber = cardNumber;
        this.myListAc = myListAc;
    }


}
