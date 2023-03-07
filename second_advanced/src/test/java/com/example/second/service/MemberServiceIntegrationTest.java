package com.example.second.service;

import com.example.second.domain.Member;
import com.example.second.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional //(db에 저장하지 않고 rollback 처리해줌)
class MemberServiceIntegrationTest {

    MemberService memberService;
    MemberRepository memberRepository;
    @Autowired
    public void MemberServiceIntegrationTest(MemberService memberService, MemberRepository memberRepository) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("홍길동");
        //when
        Long id = memberService.join(member);
        //then
        Member findMember = memberService.findMemberById(id).get();
        System.out.println("findMember = " + findMember);
        assertThat(findMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void findAllMembers() {
        //given
        Member member = new Member();
        member.setName("홍길동");

        Member member1 = new Member();
        member1.setName("김연아");
        //when
        Long id = memberService.join(member);
        Long id1 = memberService.join(member1);
        //then
        List<Member> members = memberService.findMembers();
        System.out.println("members = " + members);
        assertThat(members.size()).isEqualTo(2);
    }
}