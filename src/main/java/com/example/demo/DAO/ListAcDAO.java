package com.example.demo.DAO;

import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.AdvancedList;
import com.example.demo.mylists.MyList;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
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

    @Override
    @Transactional
    public void deleteElementById(Long myListAc_id, Long bankCard_Id) {
        BankCard bankCard = entityManager.createQuery("select b from BankCard b where b.bankCardId = :id and b.myListAc = :list", BankCard.class)
                .setParameter("id", bankCard_Id)
                .setParameter("list", findListId(myListAc_id))
                .getSingleResult();
        entityManager.remove(bankCard);
//        entityManager.createQuery("delete BankCard where myListAc = myListAc_id AND bankCardId = bankCard_Id ");

    }

    @Override
    @Transactional
    public BankCard findBankCardById(Long myListAc_id, Long bankCard_Id) {
        BankCard bankCard = entityManager.createQuery("select b from BankCard b where b.bankCardId = :id and b.myListAc = :list", BankCard.class)
                .setParameter("id", bankCard_Id)
                .setParameter("list", findListId(myListAc_id))
                .getSingleResult();
        return bankCard;
    }

    @Override
    @Transactional
    public Long getSizeBankCard(Long myListAc_id) {

        Long size = (Long) entityManager.createQuery("select count(b.bankCardId) from BankCard b where b.myListAc = :id")
                .setParameter("id", findListId(myListAc_id))
                .getSingleResult();
        //Long size = entityManager.createNativeQuery("SELECT COUNT (b) FROM bank_card b WHERE b.")

        return size;
    }

    @Override
    @Transactional
    public void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList) {
        bankCardsList.stream()
                .forEach(bankCard -> {
                    bankCard.setMyListAc(findListId(myListAc_id));
                });
//        MyListAc myListAc = entityManager.createQuery("select b from MyListAc b where b.myListAcId = :id", MyListAc.class)
//                .setParameter("id", myListAc_id)
//                .getSingleResult();

        MyListAc myListAc = entityManager.find(MyListAc.class, myListAc_id);
        myListAc.getBankCard().addAll(bankCardsList);

        entityManager.merge(myListAc);
        entityManager.flush();
    }

    @Override
    public Long findDuplicatesElements(Long id, Long json_element) {
        BankCard bankCard = entityManager.find(BankCard.class, json_element);

        Long res = entityManager.createQuery("select count(b.bankCardId) from BankCard b where b.myListAc = :id and b.cardNumber = :carNam and b.nameCard = :cartName", Long.class)
                .setParameter("id", findListId(id))
                .setParameter("carNam", bankCard.getCardNumber())
                .setParameter("cartName", bankCard.getNameCard())
                .getSingleResult();


        return res;
    }


    Comparator<BankCard> comparator = Comparator.comparing(BankCard::getCardNumber);

    @Override
    public boolean equals(Object obj) {
        return false;
    }


    @Override
    public MyList<BankCard> sort(Long id) {
        MyListAc myListAc = entityManager.find(MyListAc.class, id);
        MyList<BankCard> bankCardMyList = new MyList<>();
        myListAc.getBankCard().forEach(bankCardMyList::add);
        return bankCardMyList.sort(comparator);
    }

    @Override
    public MyList<BankCard> shuffle(Long id) {
        MyListAc myListAc = entityManager.find(MyListAc.class, id);
        MyList<BankCard> bankCardMyList = new MyList<>();
        myListAc.getBankCard().forEach(bankCardMyList::add);
        bankCardMyList.shuffle();
        return bankCardMyList;

    }


}
