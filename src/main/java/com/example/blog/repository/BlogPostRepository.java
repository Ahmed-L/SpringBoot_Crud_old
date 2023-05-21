package com.example.blog.repository;

import com.example.blog.model.BlogPost;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

//    @EntityGraph(value = "BlogPost.author", type = EntityGraph.EntityGraphType.FETCH)
//    List<BlogPost> fetchAll();

//    @EntityGraph(value = "BlogPost.author", type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT b FROM BlogPost b WHERE b.id = :id")
//    BlogPost findByIdWithAuthor(@Param("id") Long id);
//
//    @EntityGraph(value = "BlogPost.author", type = EntityGraph.EntityGraphType.LOAD)
//    @Query("SELECT b FROM BlogPost b WHERE b.id = :id")
//    BlogPost findByIdWithoutAuthor(@Param("id") Long id);
}
