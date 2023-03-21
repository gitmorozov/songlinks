package com.github.gitmorozov.songlinks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.github.gitmorozov.songlinks.dto.UserDto;
import com.github.gitmorozov.songlinks.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {
	
	@Autowired
	private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }
    
    @GetMapping("/register_processing")
    public String processRegisterWithErrors(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
    	if(bindingResult.hasErrors()){
    		return "register";
    	}
    	userService.saveUser(userDto);
    	
    	return "index";
    }
    
    @PostMapping("/register_processing")
    public String processRegister(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return "register";
        }
    	userService.saveUser(userDto);
    	
    	return "index";
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }

}
