package com.github.gitmorozov.songlinks.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.github.gitmorozov.songlinks.auth.Role;
import com.github.gitmorozov.songlinks.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryMySQLTest {
     
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
    
    @AfterAll
    public void clearDatabase() {
    	userRepo.delete(savedUser);
    }
    

}