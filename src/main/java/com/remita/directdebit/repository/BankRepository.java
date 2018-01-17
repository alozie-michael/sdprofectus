package com.remita.directdebit.repository;

import com.remita.directdebit.model.Bank;
import com.remita.directdebit.model.Mandate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("BankRepository")
public interface BankRepository extends JpaRepository<Bank, Long> {

    Bank findByBankCodeContaining(String bankcode);
}
