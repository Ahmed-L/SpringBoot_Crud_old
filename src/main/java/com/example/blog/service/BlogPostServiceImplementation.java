package com.example.blog.service;

import com.example.blog.model.BlogPost;
import com.example.blog.model.Topic;
import com.example.blog.model.User;
import com.example.blog.repository.BlogPostRepository;
import com.example.blog.repository.TopicRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostServiceImplementation implements BlogPostService{

    private final BlogPostRepository blogPostRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    BlogPostServiceImplementation(BlogPostRepository blogPostRepository, UserRepository userRepository, TopicRepository topicRepository)
    {
        this.blogPostRepository=blogPostRepository;
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    @Override
    public Optional<BlogPost> getBlogPostById(Long id, Boolean lazyLoading) {
        Optional<BlogPost> fetchedBlogPost = blogPostRepository.findById(id);
        if(fetchedBlogPost.isPresent())
        {
            return fetchedBlogPost;
        }
        else
        {
            return null;
        }
    }
    @Override
    public Optional<BlogPost> updateBlogById(Long id, BlogPost blogPost) {
        Optional<BlogPost> optionalBlogPost = blogPostRepository.findById(id).map(element -> {
            if(blogPost.getTitle()!=null)
                element.setTitle(blogPost.getTitle());
            if(blogPost.getContent()!=null)
                element.setContent(element.getContent());
            if(blogPost.getLikes()!=0)
                element.setLikes(blogPost.getLikes());

            return blogPostRepository.save(element);
        });
        return optionalBlogPost;
    }

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        Optional<User> user = userRepository.findById(blogPost.getAuthorId());
        Optional<Topic> topic = topicRepository.findById(blogPost.getTopicId());
        blogPost.setAuthor(user.get());
        blogPost.setTopic(topic.get());

        return blogPostRepository.save(blogPost);
    }

    @Override
    public String deleteBlogPost(Long id) {
        Optional<BlogPost> blogPost = blogPostRepository.findById(id);
        if(blogPost.isPresent())
        {
            blogPostRepository.delete(blogPost.get());
            return "Successfully deleted blog post.";
        }
        else
        {
            return "No blog post was found with the given ID.";
        }
    }

    public Page<BlogPost> getPagesWithPagination(int offset, int pageSize, String field)
    {
        Page<BlogPost> blogPosts = blogPostRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
        return blogPosts;
    }
}
