package com.example.demo.DAO;

import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ListAcDAO implements ListDAO{

    @PersistenceContext
    private  EntityManager entityManager;

    @Override
    public List<MyListAc> findAll() {

        return entityManager.createQuery("select e from MyListAc e", MyListAc.class).getResultList();
    }

    @Override
    public MyListAc findListId(Long myListAc_id) {
        return entityManager.find(MyListAc.class, myListAc_id);
    }

    @Override
    @Transactional
    public BankCard creatCardById(BankCard bankCard, Long myListAc_id) {
        bankCard.setMyListAc(findListId(myListAc_id));
        entityManager.persist(bankCard);
        entityManager.flush();
        return bankCard;
    }

    @Override
    @Transactional
    public MyListAc creatListAc(MyListAc myListAc) {
        entityManager.persist(myListAc);
        entityManager.flush();
        return myListAc;
    }

}
