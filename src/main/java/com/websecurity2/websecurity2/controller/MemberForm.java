package com.websecurity2.websecurity2.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {

    private String name;
    private String password;
    private String role;

    public MemberForm(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }
}
