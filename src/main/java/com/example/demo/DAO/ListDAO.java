package com.example.demo.DAO;


import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;

import java.util.List;

public interface ListDAO {

  List<MyListAc> findAll(String account_login);
  MyListAc findListId(Long myListAc_id);
  Boolean idArrangedLogin(Long list_id, String accountLogin);
  Account findAccountLogin(String login_account);
  BankCard creatCardById(BankCard bankCard, Long myListAc_id);
  void creatListAc(MyListAc myListAc, String login_account);
  void deleteElementById(Long myListAc_id, Long bankCard_Id);
  BankCard findBankCardById(Long myListAc_id, Long bankCard_Id);
  Long getSizeBankCard(Long myListAc_id);
  void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList);
  Long findDuplicatesElements(Long id, Long json_element);
  MyList<BankCard> sort(Long id);
  MyList<BankCard> shuffle(Long id);
}
