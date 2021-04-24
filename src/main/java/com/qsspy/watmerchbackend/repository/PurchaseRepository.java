package com.qsspy.watmerchbackend.repository;

import com.qsspy.watmerchbackend.entity.Purchase;
import com.qsspy.watmerchbackend.entity.ShopUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    Page<Purchase> findByUser(ShopUser user, Pageable pageable);
}
