package com.github.gitmorozov.songlinks.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.github.gitmorozov.songlinks.SonglinksApplication;
import com.github.gitmorozov.songlinks.config.WebSecurityConfig;
import com.github.gitmorozov.songlinks.service.UserService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { SonglinksApplication.class, WebSecurityConfig.class })
@TestPropertySource(locations = { "classpath:application-test.properties" })
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@BeforeAll
	@Sql(scripts = { "classpath:test-sql-scripts/data.sql" })
	void initDb() {

	}

	@Test
	void adminCanSeeUserList() throws Exception {
		mockMvc.perform(get("/admin/users")
				.with(user("admin").authorities(new SimpleGrantedAuthority("ADMIN"))))
				.andExpect(view().name("admin/users")).andExpect(status().isOk());
	}

	@Test
	void userCanNotSeeUserList() throws Exception {
		mockMvc.perform(get("/admin/users").with(user("user").authorities(new SimpleGrantedAuthority("USER"))))
				.andExpect(status().is4xxClientError());
	}
	
	@AfterAll
	@Sql(scripts = { "classpath:test-sql-scripts/clean-db.sql" })
	void cleanDb() {

	}

}
