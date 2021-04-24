package com.qsspy.watmerchbackend.repository;

import com.qsspy.watmerchbackend.entity.ShopUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<ShopUserDetails, Long> {
}
