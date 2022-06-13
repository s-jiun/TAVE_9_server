package com.tave_app_1.senapool.user.repository;

import com.tave_app_1.senapool.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Member, Integer> {
    Member save(Member member);
    Optional<Member> findByEmail(String email);
    Member findByUserPK(Integer userPK);
}
