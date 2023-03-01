package com.github.gitmorozov.songlinks.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.github.gitmorozov.songlinks.auth.Role;

class UserTest {

	@MockBean
	private User user;

	@ParameterizedTest
	@MethodSource("createUserRoles")
	void getAuthoritiesShouldReturnCollection(Set<Role> roleSet, List<GrantedAuthority> expectedList) {
		user = new User();
		user.setRoles(roleSet);
		List<GrantedAuthority> givenAuthorities = (List<GrantedAuthority>) user.getAuthorities();
		assertTrue(givenAuthorities.size() == expectedList.size()
				&& givenAuthorities.containsAll(expectedList)
				&& expectedList.containsAll(expectedList));
	}
	
	@Test void getAuthoritiesShouldReturnNullWhenNoRole() {
		user = new User();
		user.setRoles(null);
		assertTrue(user.getAuthorities() == null);
	}
	

	private static Stream<Arguments> createUserRoles() {
		return Stream.of(
				Arguments.of(
						createRoleSet(Role.USER, Role.MODERATOR, Role.ADMIN),
						createExpectedAuthoritiesList("USER", "MODERATOR", "ADMIN")
						),
				Arguments.of(
						createRoleSet(Role.USER, Role.ADMIN),
						createExpectedAuthoritiesList("USER", "ADMIN")
						),
				Arguments.of(
						createRoleSet(Role.USER),
						createExpectedAuthoritiesList("USER")
						),
				Arguments.of(
						createRoleSet(Role.ADMIN),
						createExpectedAuthoritiesList("ADMIN")
						)
				);
	}
	
	private static List<GrantedAuthority> createExpectedAuthoritiesList(String... roles) {
		if (roles == null) return null;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return  authorities;
	}
	
	private static Set<Role> createRoleSet(Role... userRoles) {
		if (userRoles == null) return null;
		Set<Role> roles = new HashSet<>();
		for (Role role : userRoles) {
			roles.add(role);
		}
		return roles;
	}

}
