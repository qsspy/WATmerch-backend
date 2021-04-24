package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.CreditCard;

import java.util.List;

public interface ICreditCardService {

    CreditCard postCreditCard(CreditCard creditCard, String authString);
    List<CreditCard> getCreditCards(String authString);
    void deleteCreditCard(long creditCardId);
}
