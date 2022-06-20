package com.tave_app_1.senapool.user;

import com.tave_app_1.senapool.entity.User;
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
        User UserA = new User("testUser", userService.encryptPassword("1234"), "email","pic");

        //when
        User joinUser = userService.join(UserA);
        User findUser = userRepository.findByUserPK(joinUser.getUserPK());

        //then
        assertThat(joinUser.getUserId()).isEqualTo(findUser.getUserId());
    }

    @Test
    void update() {
        //given
        User User = userRepository.findByUserPK(3);
        User UserA = new User("testUserUpdate", userService.encryptPassword("1234"), "kjesjs","pic");
        //when

        User updateUser = userService.userInfoUpdate(UserA);
        User findUpdateUser = userRepository.findByUserPK(updateUser.getUserPK());

        //then
        assertThat(findUpdateUser.getUserId()).isEqualTo("testUserUpdate");
    }
}

