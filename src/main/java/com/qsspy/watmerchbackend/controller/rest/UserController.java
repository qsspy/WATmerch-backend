package com.qsspy.watmerchbackend.controller.rest;

import com.jayway.jsonpath.InvalidJsonException;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.entity.ShopUserDetails;
import com.qsspy.watmerchbackend.exception.login.UserNotFoundException;
import com.qsspy.watmerchbackend.exception.login.WrongPasswordException;
import com.qsspy.watmerchbackend.exception.register.RegisterException;
import com.qsspy.watmerchbackend.model.UserAndPasswordModel;
import com.qsspy.watmerchbackend.service.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Zapisuje nowego użytkownika (register)",
                  notes = "Zapisuje nowego użytkownika. Nie wymaga autoryzacji")
    @PostMapping("/register")
    public ShopUser register(
            @ApiParam(name = "Nowy użytkownik") @RequestBody ShopUser user
    ) throws RegisterException {

        return userService.register(user);
    }

    @ApiOperation(value = "Pobranie danych użytkownika (logowanie)")
    @PostMapping("/loginUser")
    public ShopUser attemptLogin(
            @ApiParam(name = "Dane logowania") @RequestBody UserAndPasswordModel credsModel
    ) throws UserNotFoundException, WrongPasswordException {

        if(credsModel == null || credsModel.getPassword() == null || credsModel.getUsername() == null) {
            throw new InvalidJsonException("Data is not correct.");
        }
        return userService.getUser(credsModel.getUsername(), credsModel.getPassword());
    }

    @ApiOperation(value = "Edycja szczegółowych danych użytkownika",
                  notes = "Edycja szczegółowych danych użytkownika identyfikowanego z nagłowka 'Authorization'")
    @PutMapping("/editUserDetails")
    public ShopUserDetails editUserDetails(
            @RequestHeader(name = "Authorization") String authString,
            @ApiParam(name = "Szcegóły użytkownika") @RequestBody ShopUserDetails details
    ) {
        return userService.editUserDetails(details, authString);
    }
}
