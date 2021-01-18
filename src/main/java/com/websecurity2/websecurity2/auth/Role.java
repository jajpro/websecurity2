package com.websecurity2.websecurity2.auth;

import lombok.Getter;

@Getter
public enum Role {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String value;

    Role(String value) {
        this.value = value;
    }
}
