package com.example.demo.servic;


import com.example.demo.DTO.MyListAcResponseDTO;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;

import java.util.List;

public interface ListService {
    List<MyListAc> findAllList();
    MyListAc findIdList(Long myListAc_id);
    BankCard creatCardById(BankCard bankCard, Long myListAc_id);
    MyListAc creatMyListAc(MyListAc myListAc);
    void deleteElementById(Long myListAc_id, Long bankCard_Id);
    BankCard findBankCardById(Long myListAc_id, Long bankCard_Id);
    Long getSizeBankCard(Long myListAc_id);
    void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList);
    Long findDuplicatesElements(Long id, Long json_element);
}
