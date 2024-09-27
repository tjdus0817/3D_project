package com.rubypaper.repository;

import com.rubypaper.entity.Member;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
	boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByNick(String usernick);
    
    Optional<Member> findByEmail(String email);
}

