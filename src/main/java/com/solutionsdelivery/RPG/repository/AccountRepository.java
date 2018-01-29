package com.solutionsdelivery.RPG.repository;

import com.solutionsdelivery.RPG.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BankRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByAccountNumberContaining(String accountNumber);
}
