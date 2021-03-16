package com.example.demo.servic;


import com.example.demo.DTO.MyListAcResponseDTO;
import com.example.demo.entity.MyListAc;

import java.util.List;

public interface ListService {
    List<MyListAc> findAllList();
    MyListAc findIdList(Long myListAc_id);
}
