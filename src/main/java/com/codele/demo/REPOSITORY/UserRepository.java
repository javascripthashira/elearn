package com.codele.demo.REPOSITORY;

import com.codele.demo.ENTITY.Content;
import com.codele.demo.ENTITY.Role;
import com.codele.demo.ENTITY.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String username);
    User findByRole(Role role);
}
