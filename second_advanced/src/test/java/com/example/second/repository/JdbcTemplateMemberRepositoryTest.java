package com.example.second.repository;

import com.example.second.domain.Member;
import com.example.second.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class JdbcTemplateMemberRepositoryTest {
    MemberRepository memberRepository;
    @Autowired
    public void MemberServiceIntegrationTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Test
    void save() {
        Member member = new Member();
        member.setName("zzz");
        Member saveMember = memberRepository.save(member);
        System.out.println("saveMember = " + saveMember);
        assertThat(saveMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        Member member = new Member();
        member.setName("xxx");
        Member saveMember = memberRepository.save(member);

        Member member1 = new Member();
        member1.setName("yyy");
        Member saveMember1= memberRepository.save(member1);

        List<Member> all = memberRepository.findAll();
        System.out.println("all = " + all);
        assertThat(all.size()).isEqualTo(2);

    }
}