package com.tave_app_1.senapool.user;

import com.tave_app_1.senapool.entity.Member;
import com.tave_app_1.senapool.user.repository.UserRepository;
import com.tave_app_1.senapool.user.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PasswordEncodedTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Test
    public void 비밀번호_일치_테스트() {
        Member member = new Member("hello", userService.encryptPassword("1234"), "email","pic");

        Assertions.assertThat(bCryptPasswordEncoder.matches("1234",member.getPassword())).isEqualTo(true);
    }

    @Test
    public void 같은_비밀번호_다른_해시값_테스트() {
        Member memberA = new Member("memberA", userService.encryptPassword("1234"), "email","pic");
        Member memberB = new Member("memberB", userService.encryptPassword("1234"), "email", "pic");

        assertThat(memberA.getPassword()).isNotEqualTo(memberB.getPassword());
        Assertions.assertThat(bCryptPasswordEncoder.matches("1234",memberA.getPassword())).isEqualTo(true);
        Assertions.assertThat(bCryptPasswordEncoder.matches("1234","$2a$10$7gF7zAgCokpi/aaAIYsFSuOMR/ku3vIGrjuZGYtMctwZiVXLj1MIC")).isEqualTo(true);


    }

    @Test
    public void 이메일_조회_테스트() {
        //given
        Member member = new Member("memberC","1234","rkdejr2321@naver.com","pic");

        //when
        Member joinMember = userService.join(member);
        Optional<Member> findMember = userRepository.findByEmail(joinMember.getEmail());

        //then

    }
}
