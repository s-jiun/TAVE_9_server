package com.tave_app_1.senapool.user;

import com.tave_app_1.senapool.entity.User;
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
        User User = new User("hello", userService.encryptPassword("1234"), "email","pic");

        Assertions.assertThat(bCryptPasswordEncoder.matches("1234",User.getPassword())).isEqualTo(true);
    }

    @Test
    public void 같은_비밀번호_다른_해시값_테스트() {
        User UserA = new User("UserA", userService.encryptPassword("1234"), "email","pic");
        User UserB = new User("UserB", userService.encryptPassword("1234"), "email", "pic");

        assertThat(UserA.getPassword()).isNotEqualTo(UserB.getPassword());
        Assertions.assertThat(bCryptPasswordEncoder.matches("1234",UserA.getPassword())).isEqualTo(true);
        Assertions.assertThat(bCryptPasswordEncoder.matches("1234","$2a$10$7gF7zAgCokpi/aaAIYsFSuOMR/ku3vIGrjuZGYtMctwZiVXLj1MIC")).isEqualTo(true);


    }

    @Test
    public void 이메일_조회_테스트() {
        //given
        User User = new User("UserC","1234","rkdejr2321@naver.com","pic");

        //when
        User joinUser = userService.join(User);
        Optional<User> findUser = userRepository.findByEmail(joinUser.getEmail());

        //then

    }
}
