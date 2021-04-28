package com.qsspy.watmerchbackend.controller.rest;

import com.qsspy.watmerchbackend.entity.Purchase;
import com.qsspy.watmerchbackend.exception.login.LoginException;
import com.qsspy.watmerchbackend.service.IPurchaseService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PurchaseController {

    private IPurchaseService purchaseService;

    public PurchaseController(IPurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    // Wykonanie zakupu
    @PostMapping("/buy")
    public String makePurchase(
            @RequestHeader(name = "Authorization", required = false) String authString,
            @RequestBody Purchase purchase) throws LoginException {

        purchaseService.makePurchase(purchase, authString);
        return "Purchase completed!";
    }

    // Pobranie listy zakupów
    @GetMapping("/purchases")
    public Page<Purchase> getPurchases(
            @RequestHeader(name = "Authorization") String authString,
            @RequestParam(defaultValue = "20") Integer size,
            @RequestParam(defaultValue = "0") Integer page
    ) {

        return purchaseService.getPurchases(authString, size, page);
    }
}
