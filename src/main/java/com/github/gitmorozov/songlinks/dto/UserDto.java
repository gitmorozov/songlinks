package com.github.gitmorozov.songlinks.dto;

import com.github.gitmorozov.songlinks.validator.Agreement;
import com.github.gitmorozov.songlinks.validator.EmailNotExist;
import com.github.gitmorozov.songlinks.validator.PasswordMatches;
import com.github.gitmorozov.songlinks.validator.UserNotExist;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@PasswordMatches
public class UserDto {
	
    @NotNull
    @NotEmpty(message = "Username can not be empty")
    @UserNotExist
    private String username;

	@NotEmpty(message = "Email can not be empty")
	@Email(message = "Email can not be empty")
	@EmailNotExist
	private String email;
    
    @NotNull
    @NotEmpty(message = "Password can not be empty")
    private String password;
    
    private String matchingPassword;
    
    @Agreement
    private boolean agreement;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

	public boolean isAgreement() {
		return agreement;
	}

	public boolean getAgreement() {
		return agreement;
	}

	public void setAgreement(boolean agreement) {
		this.agreement = agreement;
	}
    
}
