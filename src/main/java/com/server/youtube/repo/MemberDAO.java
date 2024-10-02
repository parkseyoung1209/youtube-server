package com.server.youtube.repo;

import com.server.youtube.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberDAO  extends JpaRepository<Member, String> {

    // 이메일로 조회
    // SELECT * FROM member WHERE email = ''
    @Query(value = "SELECT * FROM member WHERE email = :email", nativeQuery = true)
    Optional<Member> findByEmail(@Param("email") String email);
}
