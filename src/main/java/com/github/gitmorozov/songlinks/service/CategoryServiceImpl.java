package com.github.gitmorozov.songlinks.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.gitmorozov.songlinks.dto.CategoryDto;
import com.github.gitmorozov.songlinks.dto.CategoryNode;
import com.github.gitmorozov.songlinks.entity.Category;
import com.github.gitmorozov.songlinks.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	@Override
	public void saveCategory(CategoryDto categoryDto) {
		System.out.println("------------------------ saving category");
		Category category = new Category();
		category.setName(categoryDto.getCategoryName());
		category.setSlug(categoryDto.getSlug());
		if (categoryDto.getParentId() != 0) {
			Category parent = categoryRepo.findById(categoryDto.getParentId()).orElse(null);
			System.out.println("------------- found parent: " + parent.getName());
			category.setParent(parent);
		}

		categoryRepo.save(category);
	}
	
	@Override
	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	@Override
	public List<CategoryNode> getTree() {
		List<CategoryNode> tree = categoryRepo.categoryFullTree();
		System.out.println("--------------------------------------");
		System.out.println(tree);
		System.out.println("--------------------------------------");
		return tree;
	}

}
