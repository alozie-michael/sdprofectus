package com.solutionsdelivery.OTP.repository;

import com.solutionsdelivery.OTP.model.AccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountNumberRepository extends JpaRepository<AccountNumber, Long> {

}
