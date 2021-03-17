package com.example.demo.controller;

import com.example.demo.DTO.BankCardRequestDTO;
import com.example.demo.DTO.ListAcRequestDTO;
import com.example.demo.DTO.MyListAcIdResponseDTO;
import com.example.demo.DTO.MyListAcResponseDTO;
import com.example.demo.servic.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class ControllerList {

//    @Autowired
//     private ListService listService;

    private final ListService listService;

    @Autowired
    public ControllerList(ListService listService){
        this.listService = listService;
    }


    @GetMapping("/lists")
    public List<MyListAcResponseDTO> findAllAc(){

        return listService.findAllList().stream().map(MyListAcResponseDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/lists/{id}")
    public MyListAcIdResponseDTO findAlllist(@PathVariable Long id){

        return MyListAcIdResponseDTO.from(listService.findIdList(id));
    }

    @PostMapping("/lists/{id}/add_element")
    public ResponseEntity addNewCard(@PathVariable Long id, @RequestBody BankCardRequestDTO bankCardRequestDTO){
       listService.creatCardById(bankCardRequestDTO.toBancCard(), id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/lists")
    public ResponseEntity addNewListAc(@RequestBody ListAcRequestDTO listAcRequestDTO){
        listService.creatMyListAc(listAcRequestDTO.toLisAc());
        return ResponseEntity.ok().build();
    }




}
