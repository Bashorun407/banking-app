package com.bashenterprise.banking.mapper;

import com.bashenterprise.banking.dto.AccountDto;
import com.bashenterprise.banking.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account){

        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }

    public static Account mapToAccount(AccountDto accountDto){

        return new Account(
                accountDto.id(),
                accountDto.accountHolderName(),
                accountDto.balance()
        );
    }
}
