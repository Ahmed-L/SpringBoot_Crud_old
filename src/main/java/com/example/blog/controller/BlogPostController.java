package com.example.blog.controller;


import com.example.blog.model.BlogPost;
import com.example.blog.service.BlogPostServiceImplementation;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/v1")
public class BlogPostController {

    private final BlogPostServiceImplementation blogPostServiceImplementation;

    BlogPostController(BlogPostServiceImplementation blogPostServiceImplementation)
    {
        this.blogPostServiceImplementation=blogPostServiceImplementation;
    }

    @GetMapping("/blogs")
    List<BlogPost> getAllBlogs()
    {
        return blogPostServiceImplementation.getAllBlogPosts();
    }

    @GetMapping("/blogs/paginate/{offset}/{pageSize}/{field}")
    Page<BlogPost> getAllBlogsPagination(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field)
    {
        Page<BlogPost> page = blogPostServiceImplementation.getPagesWithPagination(offset, pageSize, field);
        return page;

    }
    @GetMapping("/blogs/{id}/{lazyLoading}")
    Optional<BlogPost> getBlogById(@PathVariable Long id, @PathVariable Boolean lazyLoading)
    {
        Optional<BlogPost> optionalBlogPost = blogPostServiceImplementation.getBlogPostById(id, lazyLoading);
        if(lazyLoading)
            Hibernate.initialize(optionalBlogPost.get());

        return optionalBlogPost;
    }
    @GetMapping("/test")
    String test()
    {
        return "test";
    }

    @PutMapping("/blogs/{id}")
    Optional<BlogPost> updateBlogPostById(@PathVariable Long id, @RequestBody BlogPost blogPost)
    {
        return blogPostServiceImplementation.updateBlogById(id, blogPost);
    }

    @PostMapping("/blogs")
    BlogPost createNewBlogPost(@RequestBody BlogPost blogPost)
    {
        return blogPostServiceImplementation.createBlogPost(blogPost);
    }
    @DeleteMapping("/blogs/{id}")
    String deleteBlogPost(@PathVariable Long id)
    {
        return blogPostServiceImplementation.deleteBlogPost(id);
    }
}
