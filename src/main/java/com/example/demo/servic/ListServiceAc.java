package com.example.demo.servic;

import com.example.demo.DAO.ListDAO;
import com.example.demo.DTO.MyListAcResponseDTO;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListServiceAc implements ListService {

    @Autowired
    private ListDAO listDAO;

    @Override
    public List<MyListAc> findAllList() {
        return listDAO.findAll();
    }

    @Override
    public MyListAc findIdList(Long myListAc_id) {
        return listDAO.findListId(myListAc_id);
    }

    @Override
    public BankCard creatCardById(BankCard bankCard, Long myListAc_id) {
        return listDAO.creatCardById(bankCard, myListAc_id);
    }

    @Override
    public MyListAc creatMyListAc(MyListAc myListAc) {
        return listDAO.creatListAc(myListAc);
    }

    @Override
    public void deleteElementById(Long myListAc_id, Long bankCard_Id) {

        listDAO.deleteElementById(myListAc_id,bankCard_Id);
    }

    @Override
    public BankCard findBankCardById(Long myListAc_id, Long bankCard_Id) {
        return listDAO.findBankCardById(myListAc_id, bankCard_Id);
    }

    @Override
    public Long getSizeBankCard(Long myListAc_id) {
        return listDAO.getSizeBankCard(myListAc_id);
    }

    @Override
    public void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList) {
        listDAO.addNListBankCard(myListAc_id, bankCardsList);
    }

    @Override
    public Long findDuplicatesElements(Long id, Long json_element) {
        return listDAO.findDuplicatesElements(id,json_element);
    }

    @Override
    public MyList<BankCard> sort(Long id) {
        return listDAO.sort(id);
    }

    @Override
    public MyList<BankCard> shuffle(Long id) {
        return listDAO.shuffle(id);
    }

}
