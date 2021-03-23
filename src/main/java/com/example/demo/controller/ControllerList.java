package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.mylists.MyList;
import com.example.demo.servic.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/user")
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
        String account_login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return listService.findAllList(account_login).stream().map(MyListAcResponseDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/lists/{id}")
    public ResponseEntity<MyListAcIdResponseDTO> findAllList(@PathVariable Long id){
        String account_login = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(listService.idArrangedLogin(id, account_login)){
            return ResponseEntity.ok(MyListAcIdResponseDTO.from(listService.findIdList(id)));
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/lists/{id}/element")
    public ResponseEntity addNewCard(@PathVariable Long id, @RequestBody BankCardRequestDTO bankCardRequestDTO){
       listService.creatCardById(bankCardRequestDTO.toBancCard(), id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/lists")
    public ResponseEntity addNewListAc(@RequestBody ListAcRequestDTO listAcRequestDTO){
        String login_account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listService.creatMyListAc(listAcRequestDTO.toLisAc(), login_account);
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

    @GetMapping("/lists/{id}/sort")
    public MyList<BankCard> sort(@PathVariable Long id){
        return listService.sort(id);
    }

    @GetMapping("/lists/{id}/shuffle")
    public MyList<BankCard> shuffle(@PathVariable Long id){
        return listService.shuffle(id);

    }

}
