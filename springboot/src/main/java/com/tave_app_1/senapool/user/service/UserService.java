package com.tave_app_1.senapool.user.service;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.user.dto.UserDto;
import com.tave_app_1.senapool.user.dto.UserUpdateDto;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NonUniqueResultException;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> join(User signupUser) {
        Optional<User> user = userRepository.findByEmail(signupUser.getEmail());
        if(user.isPresent()) {
            return Optional.empty();
        }
        else
            return Optional.ofNullable(userRepository.save(signupUser));
    }

    public Optional<User> login(String userEmail, String password) {
        Optional<User> user = userRepository.findByEmail(userEmail);
        return (passwordEncoder.matches(password,user.get().getPassword()) ? user : Optional.empty());
    }


    public User userInfoUpdate(UserUpdateDto userUpdateDto) {
        User updateUser = userRepository.findByUserPK(userUpdateDto.getUserPk());
        updateUser.setUserId(userUpdateDto.getUserId());
        updateUser.setEmail(userUpdateDto.getEmail());
        updateUser.setUserImage(updateUser.getUserImage());

        return userRepository.save(updateUser);
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
