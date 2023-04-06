package com.github.gitmorozov.songlinks.service;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

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
		Category category = new Category();
		category.setName(categoryDto.getCategoryName());
		category.setSlug(categoryDto.getSlug());
		if (categoryDto.getParentId() != 0) {
			Category parent = categoryRepo.findById(categoryDto.getParentId()).orElse(null);
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
		return tree;
	}

	@Override
	public List<Category> getTreeWithPrefixes() {

		List<Category> categories = categoryRepo.findAll();
		TreeMap<Long, TreeSet<Category>> categoryMap = new TreeMap<>();
		for (Category category : categories) {
			long id = 0;
			if (category.getParent() != null) {
				id = category.getParent().getId();
			}
			categoryMap.putIfAbsent(id, new TreeSet<Category>());
			categoryMap.get(id).add(category);
		}
		List<Category> prefixedList = new LinkedList<>();

		return addPrefixes(0, categoryMap, prefixedList);
	}

	private List<Category> addPrefixes(long parentId, TreeMap<Long, TreeSet<Category>> categories,
			List<Category> prefixedList) {
		TreeSet<Category> children = categories.get(parentId);
		if (children != null) {
			int i = 1;
			for (Category node : children) {

				Category parent = node.getParent();
				String parentPrefix = "";
				if (parent != null) {
					parentPrefix = parent.getPrefix();
				}

				String prefix = parentPrefix + i + ".";
				i++;
				node.setPrefix(prefix);
				prefixedList.add(node);
				addPrefixes(node.getId(), categories, prefixedList);

			}
		}

		return prefixedList;
	}

}
