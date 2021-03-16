package com.example.demo.DTO;

import com.example.demo.entity.MyListAc;
import lombok.Data;


@Data
public class MyListAcResponseDTO {

    private String nameAc;

    public String getNameAc() {
        return nameAc;
    }

    public void setNameAc(String nameAc) {
        this.nameAc = nameAc;
    }

    public static MyListAcResponseDTO from(MyListAc listAc){
        MyListAcResponseDTO dto = new MyListAcResponseDTO();
        dto.setNameAc(listAc.getNameAc());
        return dto;
    }
}
