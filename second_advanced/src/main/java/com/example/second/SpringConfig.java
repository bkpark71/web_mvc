package com.example.second;

import com.example.second.repository.JdbcTemplateMemberRepository;
import com.example.second.repository.JpaMemberRepository;
import com.example.second.repository.MemberRepository;
import com.example.second.repository.MemoryMemberRepository;
import com.example.second.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        //return new MemoryMemberRepository();
        return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }
}



//    private final EntityManager em;
// //    public SpringConfig(DataSource dataSource, EntityManager em) {\
// this.em = em;