package com.github.gitmorozov.songlinks.dto;

import com.github.gitmorozov.songlinks.validator.CategoryNotExist;
import com.github.gitmorozov.songlinks.validator.ParentExist;
import com.github.gitmorozov.songlinks.validator.SlugNotExist;
import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {

    @NotEmpty(message = "Category name can not be empty")
    @CategoryNotExist
    private String categoryName;
 
    @NotEmpty(message = "Slug can not be empty")
    @SlugNotExist
    private String slug;
    
    @ParentExist(message = "Category with this id does not exist")
    private long parentId;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
    
}
