package com.rubypaper;

import com.rubypaper.dto.MemberFormDTO;
import com.rubypaper.entity.Member;
import com.rubypaper.service.MemberService;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    
    //회원가입 폼 
    @GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("memberForm", new MemberFormDTO()); // 여기서 memberForm 추가
        return "join"; // 템플릿 이름
    }
    
    //회원가입 처리
    @PostMapping("/join")
    public String join(@ModelAttribute("memberForm") @Valid MemberFormDTO memberForm, BindingResult result) {
        memberService.join(memberForm, result); // 오류를 처리하도록 수정
        if (result.hasErrors()) {
            return "join"; // 에러가 있을 경우 다시 폼으로
        }
        return "redirect:/join_result"; // 성공 후 리디렉션
    }

    // **회원가입 성공 페이지**
    @GetMapping("/join_result")
    public String JoinSuccess() {
        return "join_result"; // 성공 페이지 반환
    }
    
    // **회원 목록 보기**
    @GetMapping("/members")
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.findAllMembers()); // 모든 회원 가져오기
        return "memberlist"; // 회원 목록 페이지 템플릿
    }
    
    // 회원 삭제 기능 추가
    @PostMapping("/members/delete/{id}")
    public String deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return "redirect:/delete-success"; // 삭제 성공 후 리디렉션
    }

    // 회원 삭제 성공 페이지
    @GetMapping("/delete-success")
    public String deleteSuccess() {
        return "delete-success"; // 회원삭제 성공 페이지 반환
    }
    
    // 회원 조회 기능 추가
    @GetMapping("/members/{id}")
    public String viewMember(@PathVariable Long id, Model model) {
        Member member = memberService.findMemberById(id);
        model.addAttribute("member", member);
        return "member-detail"; // 회원 상세 페이지 템플릿
    }
    
    @PostMapping("/signin")
    public String signin(@RequestParam String email, @RequestParam String password, Model model) {
        try {
            // 로그인 검증
            Member member = memberService.login(email, password);
            model.addAttribute("username", member.getUsername());
            return "redirect:/hello"; // 로그인 성공 시 리디렉션
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "signin"; // 로그인 실패 시 로그인 페이지로 돌아감
        }
    }
    @GetMapping("/hello")
    public String hello(Model model) {	
        return "hello"; // hello.html 페이지 반환
    }
    
    @GetMapping("/signin")
    public String showSignInPage(Model model) {
        return "signin"; // signin.html로 이동
    }

}
