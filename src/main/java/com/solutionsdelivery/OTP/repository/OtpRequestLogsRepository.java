package com.solutionsdelivery.OTP.repository;

import com.solutionsdelivery.OTP.model.OtpRequestLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRequestLogsRepository extends JpaRepository<OtpRequestLogs, Long> {

}
