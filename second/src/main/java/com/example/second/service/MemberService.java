package com.example.second.service;

import com.example.second.domain.Member;
import com.example.second.repository.MemberRepository;
import com.example.second.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = new MemoryMemberRepository();
    }
    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 중복 회원 존재 여부 검증
        validateDupMember(member);
        // 회원 추가
        memberRepository.save(member);
        return member.getId();
    }

    /**
     * 중복 회원 등록 여부 확인
     */
    private void validateDupMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }

    /**
     * 전체 회원 정보 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
    /**
     * 한명의 회원정보 조회  (id 매개변수)
     */
    public Optional<Member> findMemberById(Long id){
        return memberRepository.findById(id);
    }
}
