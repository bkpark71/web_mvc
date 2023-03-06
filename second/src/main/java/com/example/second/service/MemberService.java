package com.example.second.service;

import com.example.second.domain.Member;
import com.example.second.repository.MemberRepository;
import com.example.second.repository.MemoryMemberRepository;

public class MemberService {
    private final MemberRepository memberRepository ;
    /**
     * 회원 가입
     */
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = new MemoryMemberRepository();
    }
    public Long join(Member member) {
        // 중복 회원 존재 여부 검증
        validateDupMember(member);
        // 회원 추가
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDupMember(Member member){  // .isPresent()
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }
}
