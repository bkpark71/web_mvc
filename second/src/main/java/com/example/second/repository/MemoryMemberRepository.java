package com.example.second.repository;

import com.example.second.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> map = new HashMap<Long, Member>();
    private static long seq = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++seq);
        map.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(map.get(id));  // {1L, 홍길동} 또는 null => 빈 객체
    }

    @Override
    public Optional<Member> findByName(String name) {  // name 이 unique 라는 가정하에 이 비즈니스 로직 구성
        // hongkildong
        // map.values() ==> List<Member>
//        for (Member member : map.values()) {
//            if (member.getName().equals(name)) {
//                return member;
//            }
//        }   가독성을 위해 각 연산자를 다른 라인에 적어줌
        Optional<Member> any1 = map.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        return any1;
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }

    public void clear() { // test를 위한 map clear 메서드
        map.clear();
    }
}

