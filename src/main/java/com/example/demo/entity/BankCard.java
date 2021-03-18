package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "bankCard")
public class BankCard {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Long getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(Long bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getNameCard() {
        return nameCard;
    }

    public void setNameCard(String nameCard) {
        this.nameCard = nameCard;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public MyListAc getMyListAc() {
        return myListAc;
    }

    public void setMyListAc(MyListAc myListAc) {
        this.myListAc = myListAc;
    }
}
