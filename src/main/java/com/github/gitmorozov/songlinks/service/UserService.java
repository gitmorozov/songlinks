package com.github.gitmorozov.songlinks.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.github.gitmorozov.songlinks.dto.UserDto;
import com.github.gitmorozov.songlinks.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    Page<User> findAllUsers(Pageable pageable);
    
    Page<User> findUsers(int pageNo, int pageSize, String sortField, String sortDirection);
    
}
