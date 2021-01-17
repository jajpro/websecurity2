package com.websecurity2.websecurity2.service;

import com.websecurity2.websecurity2.domain.Member;
import com.websecurity2.websecurity2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     * @param member
     * @return
     */
    @Transactional(readOnly = false)
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m->{
                    new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 로그인검증
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byName = memberRepository.findByName(username);
        Member member = byName.orElseThrow(() -> new UsernameNotFoundException(username));
        return new User(member.getName(), member.getPassword(), authorities(member.getRole()));
    }

    private Collection<? extends GrantedAuthority> authorities(String role) {
        if(role.isEmpty()) {
            //신규 ROLE 부여
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        } else {
            return Arrays.asList(new SimpleGrantedAuthority(role));
        }
    }


    /**
     * 전체회원조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 아이디조회
     * @param memberId
     * @return
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    /**
     * 이름조회
     * @param name
     * @return
     */
    public Optional<Member> findByName(String name) {
        return memberRepository.findByName(name);
    }

}