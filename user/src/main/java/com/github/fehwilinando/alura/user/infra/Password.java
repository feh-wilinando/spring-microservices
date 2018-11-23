package com.github.fehwilinando.alura.user.infra;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Password {
    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final String content;

    private Password(String raw) {
        this.content = encoder.encode(raw);
    }

    public String getEncoded() {
        return content;
    }

    public static Password of(String raw) {
        return new Password(raw);
    }

    public boolean match(String password) {
        return encoder.matches(content, password);
    }
}
