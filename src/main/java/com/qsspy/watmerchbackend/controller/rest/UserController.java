package com.qsspy.watmerchbackend.controller.rest;

import com.jayway.jsonpath.InvalidJsonException;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.entity.ShopUserDetails;
import com.qsspy.watmerchbackend.exception.login.UserNotFoundException;
import com.qsspy.watmerchbackend.exception.login.WrongPasswordException;
import com.qsspy.watmerchbackend.exception.register.RegisterException;
import com.qsspy.watmerchbackend.model.UserAndPasswordModel;
import com.qsspy.watmerchbackend.service.IUserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //Zapisuje nowego użytkownika (register)
    @PostMapping("/register")
    public ShopUser register(@RequestBody ShopUser user) throws RegisterException {

        return userService.register(user);
    }

    //Zwraca dane uzytkownika, jeżeli podano poprawne dane logowanie
    @PostMapping("/loginUser")
    public ShopUser attemptLogin(@RequestBody UserAndPasswordModel credsModel) throws UserNotFoundException, WrongPasswordException {

        if(credsModel == null || credsModel.getPassword() == null || credsModel.getUsername() == null) {
            throw new InvalidJsonException("Data is not correct.");
        }
        return userService.getUser(credsModel.getUsername(), credsModel.getPassword());
    }

    @PutMapping("/editUserDetails")
    public ShopUserDetails editUserDetails(
            @RequestBody ShopUserDetails details,
            @RequestHeader(name = "Authorization") String authString
    ) {
        return userService.editUserDetails(details, authString);
    }
}
