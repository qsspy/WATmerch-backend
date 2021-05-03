package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.CreditCard;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.model.UserAndPasswordModel;
import com.qsspy.watmerchbackend.repository.CreditCardRepository;
import com.qsspy.watmerchbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService implements ICreditCardService{

    private UserRepository userRepository;
    private CreditCardRepository creditCardRepository;

    public CreditCardService(UserRepository userRepository, CreditCardRepository creditCardRepository) {
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard postCreditCard(CreditCard creditCard, String authString) {

        String username = UserAndPasswordModel.basicAuthBase64Decode(authString).getUsername();
        ShopUser user = userRepository.findByUsername(username);
        creditCard.setUser(user);

        return creditCardRepository.save(creditCard);
    }

    @Override
    public List<CreditCard> getCreditCards(String authString) {

        String username = UserAndPasswordModel.basicAuthBase64Decode(authString).getUsername();
        ShopUser user = userRepository.findByUsername(username);
        return creditCardRepository.findByUser(user);
    }

    @Override
    public void deleteCreditCard(long creditCardId) {

        creditCardRepository.deleteById(creditCardId);
    }
}
