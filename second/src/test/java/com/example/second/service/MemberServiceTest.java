package com.example.second.service;

import com.example.second.domain.Member;
import com.example.second.repository.MemberRepository;
import com.example.second.repository.MemoryMemberRepository;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    void clear(){
        memberRepository.clear();
    }
    @Test
    void 회원_가입_테스트() throws Exception { // 정상적인 경우
        //given
        Member member = new Member();
        member.setName("홍길동");
        //when
        memberService.join(member);
        //then
        Optional<Member> findMember = memberRepository.findById(member.getId());
        assertThat(findMember.get()).isEqualTo(member);
    }

    @Test
    void 중복_회원_가입_테스트() throws Exception { // 오류 발생하는 경우
        //given
        Member member = new Member();
        member.setName("홍길동");

        Member member1 = new Member();
        member1.setName("홍길동");
        //when
        memberService.join(member);
        //then
        //에러를 발생시키도록 우리가 설계했고, 그대로 에러가 발생해야 함.
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member1));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }

    @Test
    void 전체_회원_목록_테스트(){
        //given
        Member member = new Member();
        member.setName("홍길동");

        Member member1 = new Member();
        member1.setName("김연아");
        //when
        memberService.join(member);
        memberService.join(member1);
        //then
        assertThat(memberRepository.findAll().size()).isEqualTo(2);
    }
}