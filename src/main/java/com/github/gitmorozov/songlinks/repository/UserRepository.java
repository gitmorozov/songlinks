package com.github.gitmorozov.songlinks.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.gitmorozov.songlinks.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByUsername(String username);

	Page<User> findAll(Pageable pageable);

}