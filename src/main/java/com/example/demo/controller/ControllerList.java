package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.entity.Account;
import com.example.demo.entity.BankCard;
import com.example.demo.entity.MyListAc;
import com.example.demo.mylists.MyList;
import com.example.demo.servic.ListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
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
        MyListAcIdResponseDTO dto = MyListAcIdResponseDTO.from(listService.findListIdAnswer(id).getBody());
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto,listService.findListIdAnswer(id).getStatusCode());

    }

    @PostMapping("/lists/{id}/element")
    public ResponseEntity<?> addNewCard(@PathVariable Long id, @RequestBody BankCardRequestDTO bankCardRequestDTO){
        return listService.creatCardById(bankCardRequestDTO.toBancCard(), id);
    }

    @PostMapping("/lists")
    public ResponseEntity addNewListAc(@RequestBody ListAcRequestDTO listAcRequestDTO){
        String login_account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        listService.creatMyListAc(listAcRequestDTO.toLisAc(), login_account);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/lists/{id}/elements/{id_element}")
    public ResponseEntity<?> deleteElements(@PathVariable Long id, @PathVariable Long id_element){

            return listService.deleteElementById(id, id_element);
    }

    @GetMapping("/lists/{id}/elements/{id_element}")
    public ResponseEntity<BankCardByIdResponseDTO> findBankCardById(@PathVariable Long id, @PathVariable Long id_element){
        if(listService.findBankCardById(id,id_element).getBody() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(BankCardByIdResponseDTO.fromBC(listService.findBankCardById(id,id_element).getBody()),
                listService.findBankCardById(id,id_element).getStatusCode());
    }

    @GetMapping("/lists/{id}/size")
    public ResponseEntity<Long> getSizeBankCards(@PathVariable Long id){
        if(listService.getSizeBankCard(id).getBody() == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return listService.getSizeBankCard(id);
    }

    @PutMapping("/lists/{id}/elements")
    public ResponseEntity<?> addListBankCard(@PathVariable Long id, @RequestBody ListBankCardRequestDTO listBankCardRequestDTO){
         return listService.addNListBankCard(id, listBankCardRequestDTO.fromListBankCards());
    }

    @GetMapping("/lists/{id}/find")
    public ResponseEntity<Long> findDuplicatesElements(@PathVariable Long id, @RequestParam(name = "element") Long json_element){
        if(listService.findDuplicatesElements(id, json_element).getBody() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return listService.findDuplicatesElements(id, json_element);
    }

    @GetMapping("/lists/{id}/sort")
    public ResponseEntity<MyList<BankCard>> sort(@PathVariable Long id){
        if(listService.sort(id).getBody() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return listService.sort(id);
    }

    @GetMapping("/lists/{id}/shuffle")
    public ResponseEntity<MyList<BankCard>> shuffle(@PathVariable Long id){
        if(listService.shuffle(id).getBody() == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return listService.shuffle(id);

    }

}
