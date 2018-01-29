package com.solutionsdelivery.User.repository;

import com.solutionsdelivery.User.model.RemovedUser;
import com.solutionsdelivery.User.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("RemovedUserRepository")
public interface RemovedUserRepository extends JpaRepository<RemovedUser, Long> {

}
