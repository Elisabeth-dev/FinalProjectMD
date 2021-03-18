package com.example.demo.DTO;

import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ListBankCardRequestDTO {

    private List<BankCardRequestDTO> listBankCard;


   public  List<BankCard> fromListBankCards(){
       List<BankCard> bankCardList = listBankCard.stream()
               .map(BankCardRequestDTO::toBancCard)
               .collect(Collectors.toList());
       return bankCardList;

   }

    public List<BankCardRequestDTO> getListBankCard() {
        return listBankCard;
    }

    public void setListBankCard(List<BankCardRequestDTO> listBankCard) {
        this.listBankCard = listBankCard;
    }
}
