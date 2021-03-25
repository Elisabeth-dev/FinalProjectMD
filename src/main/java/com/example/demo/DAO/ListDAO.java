package com.example.demo.DAO;


import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListDAO {

  List<MyListAc> findAll(String account_login);
  MyListAc findListId(Long myListAc_id);
  MyListAc findListIdAnswer(Long myListAc_id, String accountLogin);
  Boolean idArrangedLogin(Long list_id, String accountLogin);
  Account findAccountLogin(String login_account);
  BankCard creatCardById(BankCard bankCard, Long myListAc_id, String accountLogin);
  void creatListAc(MyListAc myListAc, String login_account);
  void deleteElementById(Long myListAc_id, Long bankCard_Id, String accountLogin);
  BankCard findBankCardById(Long myListAc_id, Long bankCard_Id, String accountLogin);
  Long getSizeBankCard(Long myListAc_id, String accountLogin);
  void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList, String accountLogin);
  Long findDuplicatesElements(Long id, Long json_element, String accountLogin);
  MyList<BankCard> sort(Long id, String name_comparator, String accountLogin);
  MyList<BankCard> shuffle(Long id, String accountLogin);
}
