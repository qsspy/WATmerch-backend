package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.User;
import org.springframework.data.domain.Page;

public interface IUserService {

    Page<User> getUsers(int page, int size, Boolean detailed, Boolean showAddresses, Role.RoleType role);
    User postUser(User user);
}
