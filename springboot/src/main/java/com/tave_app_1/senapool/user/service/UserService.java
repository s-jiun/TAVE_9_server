package com.tave_app_1.senapool.user.service;


import com.tave_app_1.senapool.entity.Member;
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

    public Member join(Member member) {
        
        return userRepository.save(member);

    }

    public Optional<Member> login(String userEmail, String password) {
        Optional<Member> member = userRepository.findByEmail(userEmail);
        return (passwordEncoder.matches(password,member.get().getPassword()) ? member : Optional.empty());
    }


    public Member userInfoUpdate(Member updateInfo) {
        Member findMember = userRepository.findByUserPK(updateInfo.getUserPK());
        findMember.setUserId(updateInfo.getUserId());
        findMember.setEmail(updateInfo.getEmail());
        Member updateMember = userRepository.save(findMember);
        return updateMember;
    }

    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
