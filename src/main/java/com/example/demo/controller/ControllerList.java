package com.example.demo.controller;

import com.example.demo.DTO.*;
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
    public MyListAcIdResponseDTO findAllList(@PathVariable Long id){

        return MyListAcIdResponseDTO.from(listService.findIdList(id));
    }

    @PostMapping("/lists/{id}/element")
    public ResponseEntity addNewCard(@PathVariable Long id, @RequestBody BankCardRequestDTO bankCardRequestDTO){
       listService.creatCardById(bankCardRequestDTO.toBancCard(), id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/lists")
    public ResponseEntity addNewListAc(@RequestBody ListAcRequestDTO listAcRequestDTO){
        listService.creatMyListAc(listAcRequestDTO.toLisAc());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/lists/{id}/elements/{id_element}")
    public ResponseEntity deleteElements(@PathVariable Long id, @PathVariable Long id_element){

        try {
            listService.deleteElementById(id, id_element);
            return ResponseEntity.ok().build();
        } catch (IndexOutOfBoundsException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lists/{id}/elements/{id_element}")
    public BankCardByIdResponseDTO findBankCardById(@PathVariable Long id, @PathVariable Long id_element){
        return  BankCardByIdResponseDTO.fromBC(listService.findBankCardById(id,id_element));
    }

    @GetMapping("/lists/{id}/size")
    public Long getSizeBankCards(@PathVariable Long id){

        return listService.getSizeBankCard(id);
    }

    @PutMapping("/lists/{id}/elements")
    public ResponseEntity addListBankCard(@PathVariable Long id, @RequestBody ListBankCardRequestDTO listBankCardRequestDTO){
        listService.addNListBankCard(id, listBankCardRequestDTO.fromListBankCards());
         return ResponseEntity.ok().build();
    }

    @GetMapping("/lists/{id}/find")
    public Long findDuplicatesElements(@PathVariable Long id, @RequestParam(name = "element") Long json_element){

        return listService.findDuplicatesElements(id, json_element);
    }

}
