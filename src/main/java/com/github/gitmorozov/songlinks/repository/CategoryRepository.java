package com.github.gitmorozov.songlinks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.gitmorozov.songlinks.entity.Category;
import com.github.gitmorozov.songlinks.entity.User;

public interface CategoryRepository extends JpaRepository<Category, Long>  {

	Category findByName(String name);
	
	Optional<Category> findById(long id);
}
