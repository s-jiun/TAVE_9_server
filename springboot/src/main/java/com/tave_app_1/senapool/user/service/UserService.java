package com.tave_app_1.senapool.user.service;


import com.tave_app_1.senapool.entity.User;
import com.tave_app_1.senapool.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public User join(User User) {

        return userRepository.save(User);

    }

    public Optional<User> login(String userEmail, String password) {
        Optional<User> User = userRepository.findByEmail(userEmail);
        return (passwordEncoder.matches(password,User.get().getPassword()) ? User : Optional.empty());
    }


    public User userInfoUpdate(User updateInfo) {
        User findUser = userRepository.findByUserPK(updateInfo.getUserPK());
        findUser.setUserId(updateInfo.getUserId());
        findUser.setEmail(updateInfo.getEmail());
        User updateUser = userRepository.save(findUser);
        return updateUser;
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
