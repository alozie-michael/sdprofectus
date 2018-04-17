package com.solutionsdelivery.directdebit.repository;

import com.solutionsdelivery.directdebit.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("select b from Bank b where b.bankCode = ?1 OR b.bankName = ?1")
    Bank findByBankCodeContaining(String bankcodeOrBankName);
}
