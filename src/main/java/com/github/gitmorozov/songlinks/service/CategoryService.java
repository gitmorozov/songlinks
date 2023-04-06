package com.github.gitmorozov.songlinks.service;

import java.util.List;

import com.github.gitmorozov.songlinks.dto.CategoryDto;
import com.github.gitmorozov.songlinks.dto.CategoryNode;
import com.github.gitmorozov.songlinks.entity.Category;

public interface CategoryService {

	 public void saveCategory(CategoryDto categoryDto);
	 
	 public List<Category> findAll();
	 
	 public List<CategoryNode> getTree();
	 
	 public List<Category> getTreeWithPrefixes();
}
