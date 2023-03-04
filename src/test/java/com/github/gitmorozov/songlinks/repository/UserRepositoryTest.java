package com.github.gitmorozov.songlinks.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import com.github.gitmorozov.songlinks.auth.Role;
import com.github.gitmorozov.songlinks.entity.User;

@DataJpaTest
@TestPropertySource(locations = { "classpath:application-test.properties" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;

	private String username = "Bob";
	private String email = "testmail5@example.com";
	private String password = "1234";

	private User user;
	private User savedUser;

	@BeforeAll
	public void createUser() {
		user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

		Set<Role> roles = new HashSet<>();
		roles.add(Role.USER);
		roles.add(Role.ADMIN);
		user.setRoles(roles);
		savedUser = userRepo.save(user);
	}

	@Test
	public void userFoundByEmailShouldBeEqualSavedUser() {
		User foundUser = userRepo.findByEmail(email);
		assertEquals(foundUser.getUsername(), username);
	}

	@Test
	public void userFoundByUsernameShouldBeEqualSavedUser() {
		User foundUser = userRepo.findByUsername(username);
		assertEquals(foundUser.getEmail(), email);
	}

	@Test
	@Sql(scripts = { "classpath:test-sql-scripts/data.sql" })
	public void userFindAllShouldReturnSixUsers() {
		List<User> users = userRepo.findAll();
		int userNumber = users.size();
		assertEquals(6, userNumber);
	}

	@AfterAll
	public void clearDatabase() {
		userRepo.delete(savedUser);
	}

}