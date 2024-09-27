package com.rubypaper.service;

import com.rubypaper.dto.MemberFormDTO;
import com.rubypaper.entity.Member;
import com.rubypaper.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 로직 (중복성 검사)
    public void join(MemberFormDTO memberForm, BindingResult result) {
        if (memberRepository.existsByEmail(memberForm.getEmail())) {
            result.rejectValue("email", "error.email", "이미 사용 중인 이메일입니다.");
        }
        
        if (memberRepository.existsByNick(memberForm.getNick())) {
            result.rejectValue("id", "error.id", "이미 사용 중인 아이디입니다.");
        }

        if (memberRepository.existsByUsername(memberForm.getUsername())) {
            result.rejectValue("username", "error.username", "이미 사용 중인 사용자명입니다.");
        }

        if (result.hasErrors()) {
            return; // 오류가 있을 경우 메서드를 종료
        }

        // 회원가입 로직
        Member member = Member.builder()
        		.nick(memberForm.getNick())
                .email(memberForm.getEmail())
                .username(memberForm.getUsername())
                .password(memberForm.getPassword())
                .build();
        memberRepository.save(member);
    }

    // 회원 삭제 로직
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. ID: " + id));
        memberRepository.delete(member);
    }

    // 모든 회원 조회 로직
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }
    
    // 특정 회원 조회 로직
    public Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다. ID: " + id));
    }
    
    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));
        if (!member.getPassword().equals(password)) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }
        return member;
    }

    }

