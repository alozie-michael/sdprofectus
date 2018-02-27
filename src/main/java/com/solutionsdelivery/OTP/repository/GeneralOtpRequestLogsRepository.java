package com.solutionsdelivery.OTP.repository;

import com.solutionsdelivery.OTP.model.GeneralOtpRequestLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralOtpRequestLogsRepository extends JpaRepository<GeneralOtpRequestLogs, Long> {

}
