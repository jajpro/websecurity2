package com.websecurity2.websecurity2.controller;

import com.websecurity2.websecurity2.domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    private Long id;
    private String name;
    private String password;

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
    }

    @Builder
    public MemberForm(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
