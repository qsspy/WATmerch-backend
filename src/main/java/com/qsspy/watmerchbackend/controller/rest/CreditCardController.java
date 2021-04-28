package com.qsspy.watmerchbackend.controller.rest;

import com.qsspy.watmerchbackend.entity.CreditCard;
import com.qsspy.watmerchbackend.service.CreditCardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CreditCardController {

    private CreditCardService creditCardService;

    public CreditCardController(CreditCardService creditCardService) {
        this.creditCardService = creditCardService;
    }

    @PostMapping("/creditCards")
    public CreditCard postCreditCard(
            @RequestBody CreditCard creditCard,
            @RequestHeader(name = "Authorization") String authString
    ) {
        return creditCardService.postCreditCard(creditCard, authString);
    }

    @GetMapping("/creditCards")
    public List<CreditCard> getCreditCards(
            @RequestHeader(name = "Authorization") String authString
    ) {
        return creditCardService.getCreditCards(authString);
    }

    @DeleteMapping("/creditCards")
    public String deleteCreditCard(
            @RequestParam Long cardId
    ) {
        creditCardService.deleteCreditCard(cardId);
        return "Card deleted successfully";
    }
}
