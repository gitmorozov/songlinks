package com.github.gitmorozov.songlinks.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.gitmorozov.songlinks.dto.CategoryDto;
import com.github.gitmorozov.songlinks.dto.CategoryNode;
import com.github.gitmorozov.songlinks.dto.UserDto;
import com.github.gitmorozov.songlinks.entity.Category;
import com.github.gitmorozov.songlinks.entity.User;
import com.github.gitmorozov.songlinks.repository.CategoryRepository;
import com.github.gitmorozov.songlinks.repository.UserRepository;
import com.github.gitmorozov.songlinks.service.CategoryService;
import com.github.gitmorozov.songlinks.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/users")
	public String showUsers(Model model, Pageable pageable) {
		model.addAttribute("users", userRepo.findAll(pageable));
		return "admin/users";
	}
	
	@GetMapping("/page/{pageNo}")
	public String showUsersPage(@PathVariable(value = "pageNo") int pageNo,
		    @RequestParam(name="sortField", required = false, defaultValue="userId") String sortField,
		    @RequestParam(name="sortDir", required = false, defaultValue="asc") String sortDir,
		    Model model) {
		
		int pageSize = 2;

	    Page<User> page = userService.findUsers(pageNo, pageSize, sortField, sortDir);
	    List<User> userList = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());

	    model.addAttribute("sortField", sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

	    model.addAttribute("userList", userList);
		
		return "admin/usersPage";
	}
	
	@GetMapping("/categories")
	public String showCategories(Model model) {
        CategoryDto category = new CategoryDto();
        model.addAttribute("category", category);
//        List<Category> categoryTree = categoryService.findAll();
//    	model.addAttribute("categoryTree", categoryTree);
        List<CategoryNode> categoryTree = categoryService.getTree();
    	model.addAttribute("categoryTree", categoryTree);
		return "admin/adminCategories";
	}
	
    @PostMapping("/addCategory")
    public String processRegister(@ModelAttribute("category") @Valid CategoryDto categoryDto, BindingResult bindingResult, Model model) {
//        System.out.println("------------------------ addCategory");
//        System.out.println("------------------------ CategoryDto.parentId = " + categoryDto.getParentId());
    	if(bindingResult.hasErrors()){
//    		System.out.println("------------------------ has errors");
            return "admin/adminCategories";
        }
    	categoryService.saveCategory(categoryDto);
    	List<Category> categoryTree = categoryService.findAll();
    	model.addAttribute("categoryTree", categoryTree);
    	
    	return "admin/adminCategories";
    }

	@GetMapping("/tree")
	public @ResponseBody List<CategoryNode> showCategoryTree(Model model) {
        List<CategoryNode> categoryTree = categoryService.getTree();
    	model.addAttribute("categoryTree", categoryTree);
		return categoryTree;
	}
}
