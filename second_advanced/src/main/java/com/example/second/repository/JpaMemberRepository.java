package com.example.second.repository;

import com.example.second.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Member save(Member member) { // 영속성, 영구 저장하다.
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        //System.out.println("result.get(0) = " + result.get(0) + ":" + result.get(0).getClass()); // 없는 경우 오류 발생하므로 이렇게 하면 안됨
        //System.out.println("Optional.of(result.get(0)) = " + Optional.of(result.get(0)) + ":" + Optional.of(result.get(0)).getClass());
        System.out.println("result.stream().findAny() = " + result.stream().findAny() + ":" + result.stream().findAny().getClass());
        return result.stream().findAny(); // Optional.of(result.get(0)); // ;
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
