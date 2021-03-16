package com.example.demo.entity;

import com.example.demo.mylists.MyList;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "myListAc")
public class MyListAc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long myListAcId;

    private String nameAc;

    @JsonBackReference
    @OneToMany( mappedBy = "myListAc")
    @Column(name = "bankCard")
    private List<BankCard> bankCard;


    public Long getMyListAcId() {
        return myListAcId;
    }

    public void setMyListAcId(Long myListAcId) {
        this.myListAcId = myListAcId;
    }

    public String getNameAc() {
        return nameAc;
    }

    public void setNameAc(String nameAc) {
        this.nameAc = nameAc;
    }

    public List<BankCard> getBankCard() {
        return bankCard;
    }

    public void setBankCard(List<BankCard> bankCard) {
        this.bankCard = bankCard;
    }
}
