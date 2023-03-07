package com.example.second.repository;

import com.example.second.domain.Member;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @BeforeEach
    void 저장소_초기화() {
        memberRepository.clear();
    }
    @Test
    @DisplayName("memberRepository save test")
    void 멤버_저장_테스트() {
        //given
        Member member = new Member();
        member.setName("hong");
        //when
        memberRepository.save(member);
        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        System.out.println("Member = " + member);
        System.out.println("findMember = " + findMember);
        assertThat(findMember.get().getId()).isEqualTo(member.getId());
    }
    @Test
    void 이름으로_멤버_찾기() {
        //given
        Member member = new Member();
        member.setName("hong");
        memberRepository.save(member);
        //when
        Optional<Member> findMember = memberRepository.findByName(member.getName());
        //then
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
        assertThat(findMember.get().getName()).isEqualTo(member.getName());
    }

    @Test
    void 전체_회원_조회() {
        //given
        Member member1 = new Member();
        member1.setName("hong");
        memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("hongkildong");
        memberRepository.save(member2);
        //when
        List<Member> allMembers = memberRepository.findAll();
        //then
        System.out.println("all = " + allMembers);
        System.out.println("allMembers.get(0) = " + allMembers.get(0));
        System.out.println("allMembers.get(1) = " + allMembers.get(1));
        assertThat(allMembers.size()).isEqualTo(2);

    }
}