package com.bashenterprise.banking.service;

import com.bashenterprise.banking.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);
    AccountDto getAccountById(Long id);
    List<AccountDto> getAllAccounts();

    AccountDto deposit(Long id, double amount);
    AccountDto withdraw(Long id, double amount);
    void deleteAccount(Long id);
}
