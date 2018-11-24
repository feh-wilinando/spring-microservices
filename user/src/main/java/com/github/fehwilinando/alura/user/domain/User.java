package com.github.fehwilinando.alura.user.domain;

import com.github.fehwilinando.alura.user.features.updatepassword.requests.ChangePasswordRequest;
import com.github.fehwilinando.alura.user.infra.Password;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String cpf;
    private String password;

    private User() { }

    public User(String name, String email, Password password, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        this.password = password.getEncoded();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassword() {
        return password;
    }

    public void updatePassword(ChangePasswordRequest request) {
        password = request.getNewPassword();
    }
}
