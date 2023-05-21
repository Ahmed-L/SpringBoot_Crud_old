package com.example.blog.service;

import com.example.blog.model.BlogPost;
import com.example.blog.model.Topic;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public interface BlogPostService {

    List<BlogPost> getAllBlogPosts();
    Optional<BlogPost> getBlogPostById(Long id, Boolean lazyLoading);
    Optional<BlogPost> updateBlogById(Long id, BlogPost blogPost);
    BlogPost createBlogPost(BlogPost blogPost);
    String deleteBlogPost(Long id);

}
