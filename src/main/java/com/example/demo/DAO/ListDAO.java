package com.example.demo.DAO;


import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;

import java.util.List;

public interface ListDAO {

  List<MyListAc> findAll();
  MyListAc findListId(Long myListAc_id);


}
