package com.example.second.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
public class MemberController {
    @GetMapping("/new")
    public String newMember() {
        return "member/new";
    }
    @GetMapping("/list")
    public String memberList() {
        return "member/list";
    }
}
