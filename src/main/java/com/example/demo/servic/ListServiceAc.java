package com.example.demo.servic;

import com.example.demo.DAO.ListDAO;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceAc implements ListService {

    @Autowired
    private ListDAO listDAO;

    @Override
    public List<MyListAc> findAllList(String account_login) {
        return listDAO.findAll(account_login);
    }

    @Override
    public ResponseEntity<MyListAc> findListIdAnswer(Long myListAc_id) {
        return listDAO.findListIdAnswer(myListAc_id);
    }


    @Override
    public Boolean idArrangedLogin(Long list_id, String accountLogin) {
        return listDAO.idArrangedLogin(list_id, accountLogin);
    }

    @Override
    public ResponseEntity<?> creatCardById(BankCard bankCard, Long myListAc_id) {
         return listDAO.creatCardById(bankCard, myListAc_id);
    }

    @Override
    public void creatMyListAc(MyListAc myListAc, String login_account) {
        listDAO.creatListAc(myListAc, login_account);
    }

    @Override
    public ResponseEntity<?> deleteElementById(Long myListAc_id, Long bankCard_Id) {
        return listDAO.deleteElementById(myListAc_id,bankCard_Id);
    }


    @Override
    public ResponseEntity<BankCard> findBankCardById(Long myListAc_id, Long bankCard_Id) {
        return listDAO.findBankCardById(myListAc_id, bankCard_Id);
    }

    @Override
    public ResponseEntity<Long> getSizeBankCard(Long myListAc_id) {
        return listDAO.getSizeBankCard(myListAc_id);
    }

    @Override
    public ResponseEntity<?> addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList) {
        return listDAO.addNListBankCard(myListAc_id, bankCardsList);
    }

    @Override
    public ResponseEntity<Long> findDuplicatesElements(Long id, Long json_element) {
        return listDAO.findDuplicatesElements(id,json_element);
    }

    @Override
    public ResponseEntity<MyList<BankCard>> sort(Long id) {
        return listDAO.sort(id);
    }

    @Override
    public ResponseEntity<MyList<BankCard>> shuffle(Long id) {
        return listDAO.shuffle(id);
    }



}
