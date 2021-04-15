package com.qsspy.watmerchbackend.controller;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.service.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    //Pobiera listę użytkowników
    @GetMapping("/users")
    public Page<ShopUser> getUsers(
            @RequestParam(defaultValue = "0") int page, // numer strony 0-indexed
            @RequestParam(defaultValue = "20") int size, // wielkość strony
            @RequestParam(defaultValue = "false") Boolean detailed,
            @RequestParam(defaultValue = "false", name = "show-addresses") Boolean showAddresses,
            @RequestParam(required = false, name = "role-type") Role.RoleType roleType
    ) {

        return userService.getUsers(page, size, detailed, showAddresses, roleType);
    }

    //Zapisuje nowego użytkownika (register)
    @PostMapping("/users")
    public ShopUser postUser(@RequestBody ShopUser user) {

        return userService.postUser(user);
    }
}
