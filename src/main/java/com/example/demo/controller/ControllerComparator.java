package com.example.demo.controller;

import com.example.demo.DAO.ComparatorDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControllerComparator {

    @Autowired
    ComparatorDAO comparatorDAO;

    @PostMapping("/user/load")
    public ResponseEntity load(@RequestBody String groovy, @RequestParam(name = "name_comparator") String name_comparator){
        comparatorDAO.creatComparator(groovy, name_comparator);
        return ResponseEntity.ok().build();
    }

}
