package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.exception.login.UserNotFoundException;
import com.qsspy.watmerchbackend.exception.login.WrongPasswordException;
import org.springframework.data.domain.Page;

public interface IUserService {

    Page<ShopUser> getUsers(int page, int size, Boolean detailed, Boolean showAddresses, Role.RoleType role);
    ShopUser postUser(ShopUser user);

    ShopUser getUser(String username, String password) throws UserNotFoundException, WrongPasswordException;
}
