package com.github.gitmorozov.songlinks.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.gitmorozov.songlinks.auth.Role;
import com.github.gitmorozov.songlinks.dto.UserDto;
import com.github.gitmorozov.songlinks.entity.User;
import com.github.gitmorozov.songlinks.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void saveUser(UserDto userDto) {
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPassword(passwordEncoder.encode(userDto.getPassword()));

		Set<Role> roles = new HashSet<>();
		roles.add(Role.USER);
		user.setRoles(roles);
		user.setEnabled(true);
		userRepo.save(user);

	}

	@Override
	public User findUserByEmail(String email) {
		return null;
	}

	@Override
	public Page<User> findAllUsers(Pageable pageable) {
		return null;
	}

	@Override
	public Page<User> findUsers(int pageNo, int pageSize, String sortField, String sortDirection) {
		Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending()
				: Sort.by(sortField).descending();

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return this.userRepo.findAll(pageable);
	}

}
