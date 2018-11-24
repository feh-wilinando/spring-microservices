package com.github.fehwilinando.alura.user.features.profile;

import com.github.fehwilinando.alura.user.features.profile.response.ProfileResponse;
import com.github.fehwilinando.alura.user.infra.Result;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final ProfileRepository repository;

    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public Result<ProfileResponse> getProfileById(Long id) {
        return repository.findById(id)
                    .map(ProfileResponse::new)
                        .map(Result::success)
                            .orElseGet(Result::fail);
    }
}
