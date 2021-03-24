package com.example.demo.servic;



import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListService {
    List<MyListAc> findAllList(String account_login);
    ResponseEntity<MyListAc> findListIdAnswer(Long myListAc_id);
    Boolean idArrangedLogin(Long list_id, String accountLogin);
    ResponseEntity<?> creatCardById(BankCard bankCard, Long myListAc_id);
    void creatMyListAc(MyListAc myListAc, String login_account);
    ResponseEntity<?> deleteElementById(Long myListAc_id, Long bankCard_Id);
    ResponseEntity<BankCard> findBankCardById(Long myListAc_id, Long bankCard_Id);
    ResponseEntity<Long> getSizeBankCard(Long myListAc_id);
    ResponseEntity<?> addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList);
    ResponseEntity<Long> findDuplicatesElements(Long id, Long json_element);
    ResponseEntity<MyList<BankCard>> sort(Long id);
    ResponseEntity<MyList<BankCard>> shuffle(Long id);

}
