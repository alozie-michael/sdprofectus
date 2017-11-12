package com.remita.ussd.repository;

import com.remita.ussd.model.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ActivitiesRepository")
public interface ActivitiesRepository extends JpaRepository<Activities, Long> {

    List<Activities> findByMsisdnContaining(String msisdn);
    Activities findBySessionIDContaining(String sessionId);
    List<Activities> findByNetworkContaining(String network);

}
