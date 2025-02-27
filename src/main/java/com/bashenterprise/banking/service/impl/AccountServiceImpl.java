package com.bashenterprise.banking.service.impl;

import com.bashenterprise.banking.dto.AccountDto;
import com.bashenterprise.banking.entity.Account;
import com.bashenterprise.banking.exception.ResourceNotFoundException;
import com.bashenterprise.banking.mapper.AccountMapper;
import com.bashenterprise.banking.repository.AccountRepository;
import com.bashenterprise.banking.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account with this id " +
                        "does not exist: " + id));
        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();

        return allAccounts.stream()
                .map(AccountMapper::mapToAccountDto).toList();
    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account with this id " +
                        "does not exist: " + id));

        double total = account.getBalance() + amount;

        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return  AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account with this id " +
                        "does not exist: " + id));
        if(amount > account.getBalance()){
            throw new ResourceNotFoundException("Insufficient balance");
        }

        double balance = account.getBalance() - amount;
        account.setBalance(balance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Account with this id does not exist."));

        accountRepository.deleteById(id);
    }
}
