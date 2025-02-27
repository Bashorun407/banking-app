package com.bashenterprise.banking.repository;

import com.bashenterprise.banking.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
