package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.User;
import com.qsspy.watmerchbackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> getUsers(int page, int size, Boolean detailed, Boolean showAddresses, Role.RoleType role) {

        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        Pageable pageable = PageRequest.of(page,size,sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public User postUser(User user) {
        return userRepository.save(user);
    }
}
