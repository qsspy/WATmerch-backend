package com.qsspy.watmerchbackend.repository;

import com.qsspy.watmerchbackend.entity.CreditCard;
import com.qsspy.watmerchbackend.entity.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    List<CreditCard> findByUser(ShopUser user);
}
