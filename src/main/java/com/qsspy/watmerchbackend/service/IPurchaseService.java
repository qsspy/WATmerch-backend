package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Purchase;
import com.qsspy.watmerchbackend.exception.login.LoginException;
import com.qsspy.watmerchbackend.model.UserAndPasswordModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPurchaseService {

    Purchase makePurchase(Purchase purchase, String base64EncodedAuthString) throws LoginException;

    Page<Purchase> getPurchases(String authString, Integer pageSize, Integer pageNumber);
}
