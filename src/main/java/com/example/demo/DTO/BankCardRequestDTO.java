package com.example.demo.DTO;

import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import lombok.Data;

@Data
public class BankCardRequestDTO {
    private String nameCard;

    private int cardNumber;

    //private MyListAc myListAc;

    public BankCard toBancCard(){
        BankCard card = new BankCard();
        card.setNameCard(this.nameCard);
        card.setCardNumber(this.cardNumber);

        return card;

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


}
