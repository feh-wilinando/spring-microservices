package com.github.fehwilinando.alura.user.features.updatepassword;

import com.github.fehwilinando.alura.user.features.updatepassword.requests.ChangePasswordRequest;
import com.github.fehwilinando.alura.user.infra.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UpdatePasswordController {

    private final UpdatePasswordService service;

    public UpdatePasswordController(UpdatePasswordService service) {
        this.service = service;
    }

    @PutMapping("{id}")
    public ResponseEntity<?> changePassword(@PathVariable Long id, @Valid  @RequestBody ChangePasswordRequest request) {
        request.setId(id);

        Result<?> result = service.updatePassword(request);

        return result.onSuccessGet(ResponseEntity.noContent()::build).orIfFailGet(ResponseEntity.badRequest()::build);

    }
}
