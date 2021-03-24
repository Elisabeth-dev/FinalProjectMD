package com.example.demo.DTO;


import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import lombok.Data;

import java.util.List;

@Data
public class MyListAcIdResponseDTO {

    private String nameAc;

    private List<BankCard> bankCard;

    public static MyListAcIdResponseDTO from(MyListAc myListAc){
        if(myListAc == null){
            return null;
        }
        MyListAcIdResponseDTO dto = new MyListAcIdResponseDTO();
        dto.setNameAc(myListAc.getNameAc());
        dto.setBankCard(myListAc.getBankCard());
        return dto;
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
