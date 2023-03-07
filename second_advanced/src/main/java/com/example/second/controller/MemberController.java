package com.example.second.controller;

import com.example.second.domain.Member;
import com.example.second.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @GetMapping("/new")
    public String newMember() {
        return "member/new";
    }
    @PostMapping("/new")
    public String newMemberPost(MemberForm memberForm) {
        Member member = new Member();
        System.out.println(memberForm.getName());
        member.setName(memberForm.getName());

        Long id = null;
        try {
            id = memberService.join(member);
            System.out.println(id + " 번 회원 등록 완료");
        } catch (IllegalStateException e) {
            System.out.println("회원등록 오류 : " + e.getMessage());
        }
        return "redirect:/";
    }
    @GetMapping("/list")
    public String memberList(Model model) {
        model.addAttribute("members",  memberService.findMembers());
        return "member/list";
    }
}
