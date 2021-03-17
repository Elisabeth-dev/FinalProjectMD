package com.example.demo.DTO;

import com.example.demo.entity.MyListAc;
import lombok.Data;

@Data
public class ListAcRequestDTO {
    private String nameAc;

    public MyListAc toLisAc(){
        MyListAc myListAc = new MyListAc();
        myListAc.setNameAc(this.nameAc);
        return myListAc;
    }

    public String getNameAc() {
        return nameAc;
    }

    public void setNameAc(String nameAc) {
        this.nameAc = nameAc;
    }
}
