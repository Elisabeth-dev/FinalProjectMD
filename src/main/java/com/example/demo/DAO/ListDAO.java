package com.example.demo.DAO;


import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;

import java.util.List;

public interface ListDAO {

  List<MyListAc> findAll();
  MyListAc findListId(Long myListAc_id);
  BankCard creatCardById(BankCard bankCard, Long myListAc_id);
  MyListAc creatListAc(MyListAc myListAc);
  void deleteElementById(Long myListAc_id, Long bankCard_Id);
  BankCard findBankCardById(Long myListAc_id, Long bankCard_Id);
  Long getSizeBankCard(Long myListAc_id);
  void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList);
  Long findDuplicatesElements(Long id, Long json_element);
}
