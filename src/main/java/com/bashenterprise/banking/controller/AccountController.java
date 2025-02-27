package com.bashenterprise.banking.controller;

import com.bashenterprise.banking.dto.AccountDto;
import com.bashenterprise.banking.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Build Account POST REST API
    @PostMapping("/create")
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    //Build Account GET REST API
    @GetMapping("/accountById/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable("id") Long accountId){
        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

    //Build Account GET all REST API
    @GetMapping("/allAccounts")
    public ResponseEntity<List<AccountDto>> getAllAccounts(){
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

//    @PutMapping("/deposit/{id}")
//    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long accountId, double deposit){
//        return ResponseEntity.ok(accountService.deposit(accountId, deposit));
//    }

    //Build Deposit PUT REST API
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable("id") Long accountId,
                                              @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.deposit(accountId, amount);
        return ResponseEntity.ok(accountDto);
    }

    //Build Withdraw PUT REST API
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable("id") Long accountId,
                                               @RequestBody Map<String, Double> amount){
        double withdraw = amount.get("amount");
        AccountDto accountDto = accountService.withdraw(accountId, withdraw);

        return ResponseEntity.ok(accountDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account successfully deleted.");
    }
}
