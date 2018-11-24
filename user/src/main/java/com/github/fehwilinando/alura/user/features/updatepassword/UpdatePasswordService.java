package com.github.fehwilinando.alura.user.features.updatepassword;

import com.github.fehwilinando.alura.user.features.updatepassword.requests.ChangePasswordRequest;
import com.github.fehwilinando.alura.user.domain.User;
import com.github.fehwilinando.alura.user.infra.Result;
import com.github.fehwilinando.alura.user.repositories.UserQueryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdatePasswordService {

    private final UserQueryRepository queryRepository;

    public UpdatePasswordService(UpdatePasswordRepository repository) {
        this.queryRepository = repository;
    }

    public Result<User> updatePassword(ChangePasswordRequest request) {

        Optional<User> optionalUser = queryRepository.findById(request.getId());

        Boolean passwordMatch = optionalUser
                                    .map(User::getPassword)
                                        .map(request::oldPasswordMatchWith)
                                            .orElse(false);

        if (!passwordMatch) {
            return Result.fail();
        }

        optionalUser.ifPresent(user -> user.updatePassword(request) );

        return optionalUser.map(Result::success).orElseGet(Result::fail);
    }
}
