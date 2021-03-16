package com.example.demo.servic;

import com.example.demo.DAO.ListDAO;
import com.example.demo.DTO.MyListAcResponseDTO;
import com.example.demo.entity.MyListAc;
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
}
