package com.example.demo.controller;

import com.example.demo.DTO.MyListAcResponseDTO;
import com.example.demo.servic.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ControllerList {

    @Autowired
     private ListService listService;

//    private final ListService listService;
//
//    @Autowired
//    public ControllerList(ListService listService){
//        this.listService = listService;
//    }


    @GetMapping("/lists")
    public List<MyListAcResponseDTO> findAllAc(){

        return listService.findAllList().stream().map(MyListAcResponseDTO::from).collect(Collectors.toList());
    }


}
