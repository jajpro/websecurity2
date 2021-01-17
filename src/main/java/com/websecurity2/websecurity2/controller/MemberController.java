package com.websecurity2.websecurity2.controller;

import com.websecurity2.websecurity2.domain.Member;
import com.websecurity2.websecurity2.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        //비밀번호 암호화
        member.setPassword(passwordEncoder.encode(form.getPassword()));
        member.setRole("ROLE_USER");
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members/signIn")
    public String signIn() {
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}