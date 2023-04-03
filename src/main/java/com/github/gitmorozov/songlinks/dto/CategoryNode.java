package com.github.gitmorozov.songlinks.dto;

public interface CategoryNode {

	Long getId();

	Long getParentId();

	String getName();

	String getSlug();

	int getLvl();

	String getOrderSequence();

	String getPrefix();

}
