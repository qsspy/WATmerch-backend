package com.qsspy.watmerchbackend.repository;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {


}
