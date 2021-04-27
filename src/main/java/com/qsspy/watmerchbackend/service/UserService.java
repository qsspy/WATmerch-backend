package com.qsspy.watmerchbackend.service;

import com.qsspy.watmerchbackend.entity.Role;
import com.qsspy.watmerchbackend.entity.ShopUser;
import com.qsspy.watmerchbackend.entity.ShopUserDetails;
import com.qsspy.watmerchbackend.exception.login.UserNotFoundException;
import com.qsspy.watmerchbackend.exception.login.WrongPasswordException;
import com.qsspy.watmerchbackend.exception.register.EmailNotAvailableException;
import com.qsspy.watmerchbackend.exception.register.RegisterException;
import com.qsspy.watmerchbackend.exception.register.UserExistsException;
import com.qsspy.watmerchbackend.model.UserAndPasswordModel;
import com.qsspy.watmerchbackend.repository.UserDetailsRepository;
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
    private UserDetailsRepository userDetailsRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserDetailsRepository userDetailsRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public Page<ShopUser> getUsers(int page, int size, Boolean detailed, Boolean showAddresses, Role.RoleType role) {

        Sort sort = Sort.by(Sort.Direction.ASC, "username");
        Pageable pageable = PageRequest.of(page,size,sort);
        return userRepository.findAll(pageable);
    }

    @Override
    public ShopUser register(ShopUser user) throws RegisterException{

        if(userRepository.findByUsername(user.getUsername()) != null) {
            throw new UserExistsException("User " + user.getUsername() + " already exists!");
        }
        if(userRepository.findByEmail(user.getEmail()) != null) {
            throw new EmailNotAvailableException("E-mail " + user.getEmail() + " is already taken!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role basicRole = new Role();
        basicRole.setId(1);
        basicRole.setName(Role.RoleType.ROLE_USER);
        user.setRole(basicRole);
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

    @Override
    public ShopUserDetails editUser(ShopUserDetails details, String authString) {

        UserAndPasswordModel userCreds = UserAndPasswordModel.basicAuthBase64Decode(authString);

        ShopUser user = userRepository.findByUsername(userCreds.getUsername());
        details.setId(user.getUserDetails().getId());
        return userDetailsRepository.save(details);

    }
}
