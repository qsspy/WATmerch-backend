package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.entity.ShopUserDetails;
import com.qsspy.watmerchbackend.exception.login.UserNotFoundException;
import com.qsspy.watmerchbackend.exception.login.WrongPasswordException;
import com.qsspy.watmerchbackend.exception.register.RegisterException;
import org.springframework.data.domain.Page;

public interface IUserService {

    Page<ShopUser> getUsers(int page, int size, String keyword, int roleId);
    ShopUser register(ShopUser user) throws RegisterException;

    ShopUser getUser(String username, String password) throws UserNotFoundException, WrongPasswordException;
    ShopUser getUser(long Id);

    ShopUserDetails editUser(ShopUserDetails details, String authString);
}
