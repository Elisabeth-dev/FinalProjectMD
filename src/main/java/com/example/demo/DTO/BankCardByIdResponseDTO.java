package com.example.demo.DTO;

import com.example.demo.entity.BankCard;
import lombok.Data;

@Data
public class BankCardByIdResponseDTO {
    private String nameCard;

    private int cardNumber;

    public static BankCardByIdResponseDTO fromBC(BankCard bankCard){
        BankCardByIdResponseDTO dto = new BankCardByIdResponseDTO();
        dto.setNameCard(bankCard.getNameCard());
        dto.setCardNumber(bankCard.getCardNumber());
        return dto;
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
