package com.tave_app_1.senapool.user.repository;

import com.tave_app_1.senapool.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User save(User User);
    Optional<User> findByEmail(String email);
    User findByUserPK(Integer userPK);
}
