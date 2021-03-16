package com.example.demo.DAO;

import com.example.demo.entity.MyListAc;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

}
