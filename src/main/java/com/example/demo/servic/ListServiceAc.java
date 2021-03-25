package com.example.demo.servic;

import com.example.demo.DAO.ListDAO;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public MyListAc findListIdAnswer(Long myListAc_id) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listDAO.findListIdAnswer(myListAc_id, accountLogin);
    }


    @Override
    public Boolean idArrangedLogin(Long list_id, String accountLogin) {
        return listDAO.idArrangedLogin(list_id, accountLogin);
    }

    @Override
    public BankCard creatCardById(BankCard bankCard, Long myListAc_id) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return listDAO.creatCardById(bankCard, myListAc_id, accountLogin);
    }

    @Override
    public void creatListAc(MyListAc myListAc) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         listDAO.creatListAc(myListAc, accountLogin);
    }

    @Override
    public void deleteElementById(Long myListAc_id, Long bankCard_Id) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listDAO.deleteElementById(myListAc_id,bankCard_Id, accountLogin);
    }


    @Override
    public BankCard findBankCardById(Long myListAc_id, Long bankCard_Id) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listDAO.findBankCardById(myListAc_id, bankCard_Id, accountLogin);
    }

    @Override
    public Long getSizeBankCard(Long myListAc_id) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listDAO.getSizeBankCard(myListAc_id, accountLogin);
    }

    @Override
    public void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listDAO.addNListBankCard(myListAc_id, bankCardsList, accountLogin);
    }

    @Override
    public Long findDuplicatesElements(Long id, Long json_element) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listDAO.findDuplicatesElements(id,json_element, accountLogin);
    }

    @Override
    public MyList<BankCard> sort(Long id, String name_comparator) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listDAO.sort(id, name_comparator, accountLogin);
    }

    @Override
    public MyList<BankCard> shuffle(Long id) {
        String accountLogin =(String)  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listDAO.shuffle(id, accountLogin);
    }



}
