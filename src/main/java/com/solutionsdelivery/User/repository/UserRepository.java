package com.solutionsdelivery.User.repository;

import com.solutionsdelivery.User.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<UserProfile, Long> {

    UserProfile findByStaffNoContaining(String staffNo);

    @Query("select u from UserProfile u where u.email = ?1 OR u.staffNo = ?1")
    UserProfile findByStaffNoOOrEmail(String staffNoOrEmail);
}
