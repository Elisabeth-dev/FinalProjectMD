package com.example.demo.DAO;

import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.ComparName;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.AdvancedList;
import com.example.demo.mylists.MyList;
import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

@Repository
public class ListAcDAO implements ListDAO{

    @PersistenceContext
    private  EntityManager entityManager;

    @Override
    public List<MyListAc> findAll(String account_login) {
        Account account = findAccountLogin(account_login);
        return entityManager.createQuery("select b from MyListAc b where b.account = :id", MyListAc.class)
        .setParameter("id", account)
        .getResultList();
    }

    @Override
    public MyListAc findListId(Long myListAc_id) {
        return entityManager.find(MyListAc.class, myListAc_id);
    }

    @Override
    public MyListAc findListIdAnswer(Long myListAc_id, String accountLogin) {
        Account account = findAccountLogin(accountLogin);
        try {
            return entityManager.createQuery("select b from MyListAc b where b.account = :ac_login and b.myListAcId = :list_id", MyListAc.class)
                    .setParameter("ac_login", account)
                    .setParameter("list_id", myListAc_id)
                    .getSingleResult();
        } catch (Exception e){
            return null;
        }
    }


    @Override
    public Boolean idArrangedLogin(Long list_id, String accountLogin) {
        Account account = findAccountLogin(accountLogin);
            Long ac = entityManager.createQuery("select count(b.myListAcId) from MyListAc b where b.account = :ac_id and b.myListAcId = :list_id", Long.class)
                    .setParameter("ac_id", account)
                    .setParameter("list_id", list_id)
                    .getSingleResult();
            return ac > 0;
    }

    @Override
    @Transactional
    public BankCard creatCardById(BankCard bankCard, Long myListAc_id, String accountLogin) {
        if(idArrangedLogin(myListAc_id, accountLogin)){
            bankCard.setMyListAc(findListId(myListAc_id));
            entityManager.persist(bankCard);
            entityManager.flush();
            return  bankCard;
        }

        return null;
    }



    @Override
    public Account findAccountLogin(String login_account) {
        Account account = entityManager.createQuery("select b from Account b where b.login = :login", Account.class)
                .setParameter("login", login_account)
                .getSingleResult();
        return account;
    }

    @Override
    @Transactional
    public void creatListAc(MyListAc myListAc, String login_account) {
        myListAc.setAccount(findAccountLogin(login_account));
        entityManager.persist(myListAc);
        entityManager.flush();
    }

    @Override
    @Transactional
    public void deleteElementById(Long myListAc_id, Long bankCard_Id, String accountLogin) {
        if(idArrangedLogin(myListAc_id, accountLogin)){
            BankCard bankCard = entityManager.createQuery("select b from BankCard b where b.bankCardId = :id and b.myListAc = :list", BankCard.class)
                    .setParameter("id", bankCard_Id)
                    .setParameter("list", findListId(myListAc_id))
                    .getSingleResult();
            entityManager.remove(bankCard);
        } else {
            throw new NullPointerException("drop");
        }
    }

    @Override
    @Transactional
    public BankCard findBankCardById(Long myListAc_id, Long bankCard_Id, String accountLogin) {
        MyListAc myListAc = findListId(myListAc_id);
        if(idArrangedLogin(myListAc.getMyListAcId(), accountLogin)){
            BankCard bankCard = entityManager.createQuery("select b from BankCard b where b.bankCardId = :id and b.myListAc = :list", BankCard.class)
                    .setParameter("id", bankCard_Id)
                    .setParameter("list", findListId(myListAc_id))
                    .getSingleResult();
            return bankCard;
        }
        return null;
    }

    @Override
    @Transactional
    public Long getSizeBankCard(Long myListAc_id, String accountLogin) {
        if(idArrangedLogin(myListAc_id, accountLogin)){
            Long size = (Long) entityManager.createQuery("select count(b.bankCardId) from BankCard b where b.myListAc = :id")
                    .setParameter("id", findListId(myListAc_id))
                    .getSingleResult();
            return size;
        }
        return null;
    }

    @Override
    @Transactional
    public void addNListBankCard(Long myListAc_id, List<BankCard> bankCardsList, String accountLogin) {
        if(idArrangedLogin(myListAc_id, accountLogin)){
            bankCardsList.stream()
                    .forEach(bankCard -> {
                        bankCard.setMyListAc(findListId(myListAc_id));
                    });

            MyListAc myListAc = entityManager.find(MyListAc.class, myListAc_id);
            myListAc.getBankCard().addAll(bankCardsList);

            entityManager.merge(myListAc);
            entityManager.flush();
        } else {
            throw  new NullPointerException("drop");
        }
    }

    @Override
    public Long findDuplicatesElements(Long id, Long json_element, String accountLogin) {
        if(idArrangedLogin(id, accountLogin)){
            BankCard bankCard = entityManager.find(BankCard.class, json_element);

            Long res = entityManager.createQuery("select count(b.bankCardId) from BankCard b where b.myListAc = :id and b.cardNumber = :carNam and b.nameCard = :cartName", Long.class)
                    .setParameter("id", findListId(id))
                    .setParameter("carNam", bankCard.getCardNumber())
                    .setParameter("cartName", bankCard.getNameCard())
                    .getSingleResult();
            return res;
        }
        return null;
    }



    public Comparator<BankCard> findComparator(String name_comparator){
        ComparatorDAO comparatorDAO = new ComparatorDAO();

        try {
            ComparName comparatorScript = entityManager.createQuery("select b from ComparName b where b.compar_name = :name", ComparName.class)
                    .setParameter("name", name_comparator)
                    .getSingleResult();
            Comparator<BankCard> comparator = comparatorDAO.load(comparatorScript.getText_Comparator());
            return comparator;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException | NoResultException e) {
            Comparator<BankCard> comparatorDefault = Comparator.comparing(BankCard::getNameCard);
            return comparatorDefault;
        }

    }


    @Override
    public boolean equals(Object obj) {
        return false;
    }


    @Override
    public MyList<BankCard> sort(Long id, String name_comparator, String accountLogin) {

        if(idArrangedLogin(id, accountLogin)){
            MyListAc myListAc = entityManager.find(MyListAc.class, id);
            MyList<BankCard> bankCardMyList = new MyList<>();
            myListAc.getBankCard().forEach(bankCardMyList::add);
            return bankCardMyList.sort(findComparator(name_comparator));
        }
        return null;
    }

    @Override
    public MyList<BankCard> shuffle(Long id, String accountLogin) {
        if(idArrangedLogin(id, accountLogin)){
            MyListAc myListAc = entityManager.find(MyListAc.class, id);
            MyList<BankCard> bankCardMyList = new MyList<>();
            myListAc.getBankCard().forEach(bankCardMyList::add);
            bankCardMyList.shuffle();
            return bankCardMyList;
        }
        return null;

    }
}
