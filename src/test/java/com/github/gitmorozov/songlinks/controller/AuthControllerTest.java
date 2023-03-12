package com.github.gitmorozov.songlinks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

import com.github.gitmorozov.songlinks.SonglinksApplication;
import com.github.gitmorozov.songlinks.config.WebSecurityConfig;
import com.github.gitmorozov.songlinks.dto.UserDto;
import com.github.gitmorozov.songlinks.repository.UserRepository;
import com.github.gitmorozov.songlinks.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthController.class)
@ContextConfiguration(classes={SonglinksApplication.class, WebSecurityConfig.class})
class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private UserRepository userRepo;
	
	@Test
	void testShowRegistrationForm() throws Exception {
	mockMvc.perform(get("/register"))
	.andDo(print())
	.andExpect(view().name("register"))
	.andExpect(status().isOk());
	}
	
	@Test
	void testRegisterProcessingWithCorrectData() throws Exception {
        UserDto newUser = new UserDto();
        newUser.setUsername("testUser");
        newUser.setEmail("test@email.com");
        newUser.setPassword("password");
        newUser.setMatchingPassword("password");
        newUser.setAgreement(true);
        
        mockMvc.perform(post("/register_processing")
        		.param("username", "testUser")
        		.param("email", "test@email.com")
        		.param("password", "password")
        		.param("matchingPassword", "password")
        		.param("agreement", "true")
                .with(csrf())
        		)
    	.andDo(print())
    	.andExpect(model().hasNoErrors())
    	.andExpect(view().name("index"))
    	.andExpect(status().isOk());
	}
	
	@ParameterizedTest
	@CsvSource({
		" , test@email.com, password, password, true",
		"testUser,  , password, password, true",
		"testUser, test1@email.com, qwerty , password, true",
		"testUser, test2@email.com, password,  , true",
		"testUser, test3@email.com, password, password, false",
		"testUser, test4@email.com, password, password, ",
		"testUser, test4@email.com, password, , true",
		"testUser, test5@email.com, , password, true",

		})
	void testRegisterProcessingWithIncorrectData(
			String username, String email, String password, String matchingPassword, String agreement
			) throws Exception {
        mockMvc.perform(post("/register_processing")
        		.param("username", username)
        		.param("email", email)
        		.param("password", password)
        		.param("matchingPassword", matchingPassword)
        		.param("agreement", agreement)
                .with(csrf())
        		)
    	.andDo(print())
    	.andExpect(model().hasErrors())
    	.andExpect(view().name("register"))
    	.andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void testNonExistingMethod() throws Exception {
		mockMvc.perform(get("/noSuchPage"))
		.andDo(print())
		.andExpect(status().is4xxClientError());
	}
}
