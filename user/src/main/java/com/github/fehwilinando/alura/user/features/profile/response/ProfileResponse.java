package com.github.fehwilinando.alura.user.features.profile.response;

import com.github.fehwilinando.alura.user.domain.User;

public class ProfileResponse {
    private User user;

    public ProfileResponse(User user) {
        this.user = user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getName() {
        return user.getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getCpf() {
        return user.getCpf();
    }
}
