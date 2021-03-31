package com.example.demo.controller;

import com.example.demo.DTO.*;
import com.example.demo.configSecurity.MyUserDetailsService.CustomUserDetails;
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
        CustomUserDetails account = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String account_login = account.getUsername();
        return listService.findAllList(account_login).stream().map(MyListAcResponseDTO::from).collect(Collectors.toList());
    }

    @GetMapping("/lists/{id}")
    public ResponseEntity<MyListAcIdResponseDTO> findAllList(@PathVariable Long id){
        MyListAcIdResponseDTO dto = MyListAcIdResponseDTO.from(listService.findListIdAnswer(id));
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping("/lists/{id}/element")
    public ResponseEntity<?> addNewCard(@PathVariable Long id, @RequestBody BankCardRequestDTO bankCardRequestDTO){
        BankCard bankCard = listService.creatCardById(bankCardRequestDTO.toBancCard(), id);
        if(bankCard == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/lists")
    public ResponseEntity<?> addNewListAc(@RequestBody ListAcRequestDTO listAcRequestDTO){
        listService.creatListAc(listAcRequestDTO.toLisAc());
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/lists/{id}/elements/{id_element}")
    public ResponseEntity<?> deleteElements(@PathVariable Long id, @PathVariable Long id_element){
        try {
            listService.deleteElementById(id, id_element);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/lists/{id}/elements/{id_element}")
    public ResponseEntity<BankCardByIdResponseDTO> findBankCardById(@PathVariable Long id, @PathVariable Long id_element){
        BankCardByIdResponseDTO dto = BankCardByIdResponseDTO.fromBC(listService.findBankCardById(id,id_element));
        if(dto == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>( dto, HttpStatus.OK);
    }

    @GetMapping("/lists/{id}/size")
    public ResponseEntity<Long> getSizeBankCards(@PathVariable Long id){
        Long size = listService.getSizeBankCard(id);
        if(size == null ){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(size, HttpStatus.OK);
    }

    @PutMapping("/lists/{id}/elements")
    public ResponseEntity<?> addListBankCard(@PathVariable Long id, @RequestBody ListBankCardRequestDTO listBankCardRequestDTO){
        try {
            listService.addNListBankCard(id, listBankCardRequestDTO.fromListBankCards());
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/lists/{id}/find")
    public ResponseEntity<Long> findDuplicatesElements(@PathVariable Long id, @RequestParam(name = "element") Long json_element){
        Long res = listService.findDuplicatesElements(id, json_element);
        if (res == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/lists/{id}/sort")
    public ResponseEntity<MyList<BankCard>> sort(@PathVariable Long id, @RequestParam(name = "name_comparator", required = false, defaultValue = "default") String name_comparator){
        MyList<BankCard> listBankCard =  listService.sort(id, name_comparator);
        if(listBankCard == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(listBankCard, HttpStatus.OK);
    }

    @GetMapping("/lists/{id}/shuffle")
    public ResponseEntity<MyList<BankCard>> shuffle(@PathVariable Long id){
        MyList<BankCard> listBankCard = listService.shuffle(id);
        if(listBankCard == null){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(listBankCard, HttpStatus.OK);

    }

}
