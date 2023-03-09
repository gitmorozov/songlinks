package com.github.gitmorozov.songlinks.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

	@GetMapping("/")
	public String hello(Model theModel) {
		return "index";
	}
}
