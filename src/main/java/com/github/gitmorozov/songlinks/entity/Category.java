package com.github.gitmorozov.songlinks.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "categories")
public class Category implements Comparable<Category> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(length = 255, unique = true, nullable = false)
	private String name;
	
	@Column(length = 255, unique = true, nullable = false)
	private String slug;
	
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;
 
    @OneToMany(mappedBy = "parent")
    private Set<Category> subCategories = new HashSet<>();
    
    @OneToMany(mappedBy = "category")
    private Set<Song> songs = new HashSet<>();
    
    @Transient
    String prefix;
    
    @Transient
    String path;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Set<Category> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	@Override
	public int compareTo(Category o) {
		return this.getName().compareTo(o.getName());
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", prefix=" + prefix + "]";
	}
	
}
