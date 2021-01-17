package com.websecurity2.websecurity2.repository;

import com.websecurity2.websecurity2.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public Member save(Member member);

    public Optional<Member> findById(Long id);

    public Optional<Member> findByName(String name);

    public List<Member> findAll();

}
