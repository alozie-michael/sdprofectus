package com.solutionsdelivery.GetStatus.repository;

import com.solutionsdelivery.GetStatus.model.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantsRepository extends JpaRepository<Merchants, Long> {
	
	Merchants findByDescriptionContaining(String desc);
	Merchants findByMerchantIdContaining(String merchantId);

}
