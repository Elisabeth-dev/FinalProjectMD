package com.example.demo.DAO;


import com.example.demo.entity.BankCard;
import com.example.demo.entity.ComparName;
import groovy.lang.GroovyClassLoader;

import org.apache.logging.log4j.util.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Transient;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

@Component
public class ComparatorDAO {

    @PersistenceContext
    private EntityManager entityManager;

    private final GroovyClassLoader loader = new GroovyClassLoader();

    public Comparator<BankCard> load(String groovy) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Object o = loader.parseClass(groovy).getDeclaredConstructor().newInstance();
        if (o instanceof Comparator){
            Comparator<BankCard> comparator = (Comparator<BankCard>) o;
            return comparator;
        }
        return null;
    }

    @Transactional
    public void creatComparator(String code, String name){
        ComparName comparName = new ComparName();
        comparName.setText_Comparator(code);
        comparName.setCompar_name(name);
        entityManager.persist(comparName);
        entityManager.flush();
    }




}
