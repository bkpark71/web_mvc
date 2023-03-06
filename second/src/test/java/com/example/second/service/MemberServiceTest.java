package com.example.second.service;

import com.example.second.domain.Member;
import com.example.second.repository.MemberRepository;
import com.example.second.repository.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemberService memberService ;
    MemoryMemberRepository memberRepository ;

    @BeforeEach
    void setUp() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @Test
    void 회원_가입_테스트() throws Exception {
        //given
        Member member = new Member();
        member.setName("홍길동");
        //when
        memberService.join(member);
        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember.get()).isEqualTo(member);
    }
}