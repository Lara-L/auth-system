package com.project.auth_sys.domain.repository;

import com.project.auth_sys.domain.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    Optional<User> findByUsername(String username);
}
