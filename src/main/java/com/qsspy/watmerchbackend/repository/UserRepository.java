package com.qsspy.watmerchbackend.repository;

import com.qsspy.watmerchbackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
