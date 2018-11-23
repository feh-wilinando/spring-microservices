package com.github.fehwilinando.alura.user.repositories;

import com.github.fehwilinando.alura.user.domain.User;
import org.springframework.data.repository.Repository;

public interface UserCommandRepository extends Repository<User, Long> {
    void save(User user);
}
