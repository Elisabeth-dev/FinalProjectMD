package com.example.demo.servic;



import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ListService {
    List<MyListAc> findAllList(String account_login);
    MyListAc findListIdAnswer(Long myListAc_id);
    Boolean idArrangedLogin(Long list_id, String accountLogin);
    BankCard creatCardById(BankCard bankCard, Long myListAc_id);
    void creatListAc(MyListAc myListAc);
    void deleteElementById(Long myListAc_id, Long bankCard_Id);
    BankCard findBankCardById(Long myListAc_id, Long bankCard_Id);
    Long getSizeBankCard(Long myListAc_id);
    void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList);
    Long findDuplicatesElements(Long id, Long json_element);
    MyList<BankCard> sort(Long id, String name_comparator);
    MyList<BankCard> shuffle(Long id);

}
