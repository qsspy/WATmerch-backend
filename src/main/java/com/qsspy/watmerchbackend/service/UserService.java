package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.exception.login.UserNotFoundException;
import com.qsspy.watmerchbackend.exception.login.WrongPasswordException;
import com.qsspy.watmerchbackend.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder encoder) {

        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    @Override
    public Page<ShopUser> getUsers(int page, int size, Boolean detailed, Boolean showAddresses, Role.RoleType role) {

        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        Pageable pageable = PageRequest.of(page,size,sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public ShopUser postUser(ShopUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ShopUser getUser(String username, String password) throws UserNotFoundException, WrongPasswordException {

        ShopUser user = userRepository.findByUsername(username);

        if(user == null) {
            throw new UserNotFoundException(username + " not found.");
        } else if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new WrongPasswordException("Invalid password.");
        }

        return user;
    }
}
