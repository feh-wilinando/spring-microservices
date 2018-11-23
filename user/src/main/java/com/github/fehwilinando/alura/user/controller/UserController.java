package com.github.fehwilinando.alura.user.controller;

import com.github.fehwilinando.alura.user.controller.requests.ChangePasswordRequest;
import com.github.fehwilinando.alura.user.infra.Result;
import com.github.fehwilinando.alura.user.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @Valid  @RequestBody ChangePasswordRequest request) {
        request.setId(id);

        Result<?> result = service.updatePassword(request);

        return result.onSuccessGet(ResponseEntity.noContent()::build).orIfFailGet(ResponseEntity.badRequest()::build);

    }
}
