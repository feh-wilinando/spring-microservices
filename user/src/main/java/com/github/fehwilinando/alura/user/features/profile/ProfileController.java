package com.github.fehwilinando.alura.user.features.profile;

import com.github.fehwilinando.alura.user.features.profile.response.ProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.notFound;

@RestController
public class ProfileController {
    private final ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfileResponse> showProfileBy(@PathVariable Long id) {

        return service.getProfileById(id)
                        .onSuccessMap(ResponseEntity::ok)
                            .orIfFailGet(notFound()::build);
    }
}
