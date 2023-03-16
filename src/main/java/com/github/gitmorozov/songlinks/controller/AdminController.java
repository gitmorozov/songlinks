package com.github.gitmorozov.songlinks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.gitmorozov.songlinks.dto.UserDto;
import com.github.gitmorozov.songlinks.repository.UserRepository;
import com.github.gitmorozov.songlinks.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserRepository userRepo;

	@GetMapping("/users")
	public String showUsers(Model model, Pageable pageable) {
		model.addAttribute("users", userRepo.findAll(pageable));
		return "admin/users";
	}

}
