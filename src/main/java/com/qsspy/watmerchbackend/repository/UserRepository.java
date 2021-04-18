package com.qsspy.watmerchbackend.repository;

import com.qsspy.watmerchbackend.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<ShopUser, Long> {

    ShopUser findByUsername(String username);
    ShopUser findByEmail(String email);
}
