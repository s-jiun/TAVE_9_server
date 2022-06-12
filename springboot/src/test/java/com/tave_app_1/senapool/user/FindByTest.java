package com.tave_app_1.senapool.user;

import com.tave_app_1.senapool.entity.Member;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class FindByTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void findByUserPK() {
        //given
        Member memberA = new Member("testMember", userService.encryptPassword("1234"), "email","pic");

        //when
        Member joinMember = userService.join(memberA);
        Member findMember = userRepository.findByUserPK(joinMember.getUserPK());

        //then
        assertThat(joinMember.getUserId()).isEqualTo(findMember.getUserId());
    }

    @Test
    void update() {
        //given
        Member member = userRepository.findByUserPK(3);
        Member memberA = new Member("testMemberUpdate", userService.encryptPassword("1234"), "kjesjs","pic");
        //when

        Member updateMember = userService.userInfoUpdate(memberA);
        Member findUpdateMember = userRepository.findByUserPK(updateMember.getUserPK());

        //then
        assertThat(findUpdateMember.getUserId()).isEqualTo("testMemberUpdate");
    }
}

