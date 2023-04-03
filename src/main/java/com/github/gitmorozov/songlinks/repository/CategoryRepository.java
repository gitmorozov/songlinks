package com.github.gitmorozov.songlinks.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.github.gitmorozov.songlinks.dto.CategoryNode;
import com.github.gitmorozov.songlinks.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

	Optional<Category> findById(long id);

//	@Query(value = """
//			WITH RECURSIVE tree(id, parent_id, name, slug, lvl, order_sequence, prefix) AS (
//			SELECT cat.id, cat.parent_id, cat.name, cat.slug, 1 AS lvl,
//			CAST(LPAD(CAST(cat.id AS char(4000)),3,'0') as CHAR(4000)), CAST(0 AS char(20))
//			FROM categories cat WHERE parent_id IS NULL
//			UNION ALL
//			SELECT child.id, child.parent_id, child.name, child.slug, parent.lvl + 1,
//			CONCAT(parent.order_sequence,'_',LPAD(child.id,3,'0')), CONCAT(parent.prefix,'-')
//			FROM tree parent JOIN categories child ON parent.id = child.parent_id)
//			SELECT id, parent_id, CONCAT(RIGHT(prefix, LENGTH(prefix) - 1), name) as name,
//			slug, lvl, order_sequence, RIGHT(prefix, LENGTH(prefix) - 1) as prefix_string
//			from tree ORDER BY order_sequence ASC
//			""",
//			countQuery = "select 1", nativeQuery = true)
	@Query(value = """
			WITH RECURSIVE tree(id, parentId, name, slug, lvl, orderSequence, prefix) AS (
			SELECT cat.id, cat.parent_id, cat.name, cat.slug, 1 AS lvl,
			CAST(LPAD(CAST(cat.id AS char(4000)),3,'0') as CHAR(4000)), CAST(0 AS char(20))
			FROM categories cat WHERE parent_id IS NULL
			UNION ALL
			SELECT child.id, child.parent_id, child.name, child.slug, parent.lvl + 1,
			CONCAT(parent.orderSequence,'_',LPAD(child.id,3,'0')), CONCAT(parent.prefix,'-')
			FROM tree parent
			JOIN categories child
			ON parent.id = child.parent_id)
			SELECT id, parentId, CONCAT(RIGHT(prefix, LENGTH(prefix) - 1), name) as name,
			slug, lvl, orderSequence, RIGHT(prefix, LENGTH(prefix) - 1) as prefix_string
			from tree ORDER BY orderSequence ASC
			""",
			countQuery = "select 1", nativeQuery = true)
	List<CategoryNode> categoryFullTree();
}
