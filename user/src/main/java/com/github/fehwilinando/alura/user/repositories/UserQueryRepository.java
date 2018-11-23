package com.github.fehwilinando.alura.user.repositories;

import com.github.fehwilinando.alura.user.domain.User;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface UserQueryRepository extends Repository<User, Long> {
    Optional<User> findById(Long id);
}
