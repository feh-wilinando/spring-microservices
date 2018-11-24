package com.github.fehwilinando.alura.user.features.updatepassword.requests;

import com.github.fehwilinando.alura.user.infra.Password;

import javax.validation.constraints.NotBlank;

public class ChangePasswordRequest {
    private Long id;
    private Password oldPassword;
    private Password newPassword;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOldPassword() {
        return oldPassword.getEncoded();
    }

    public void setOldPassword(@NotBlank  String oldPassword) {
        this.oldPassword = Password.of(oldPassword);
    }

    public String getNewPassword() {
        return newPassword.getEncoded();
    }

    public void setNewPassword(@NotBlank  String newPassword) {
        this.newPassword = Password.of(newPassword);
    }

    public boolean oldPasswordMatchWith(String password) {
        return oldPassword.match(password);
    }
}
