package com.example.blog.controller;

import com.example.blog.model.Topic;
import com.example.blog.model.User;
import com.example.blog.service.TopicService;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/topic")
public class TopicController {
    private TopicService topicService;

    TopicController(TopicService topicService)
    {
        this.topicService=topicService;
    }

    @GetMapping("")
    List<Topic> getAllTopic()
    {
        return topicService.getAllTopic();
    }
    @GetMapping("/{id}")
    Topic getTopicById(@PathVariable Long id)
    {
        return topicService.getTopicById(id);
    }

    @PostMapping("")
    Topic createTopic(@RequestBody Topic topic)
    {
        return topicService.createTopic(topic);
    }
    @PutMapping("/{id}")
    Topic updateTopic(@PathVariable Long id, @RequestBody Topic topic)
    {
        return topicService.updateTopicById(id, topic);
    }
    @DeleteMapping("/{id}")
    String deleteTopic(@PathVariable Long id)
    {
        return topicService.deleteTopic(id);
    }

    @GetMapping(path = "ch/{lazyLoading}")
    List<Topic> getAllTopicsWithLazyLoading(@PathVariable Boolean lazyLoading)
    {
        if(lazyLoading)
            return topicService.findTopicsWithBlogPosts();

        List<Topic> topics= topicService.findTopicsWithBlogPosts();
        return topics.stream().map(element->{
            Hibernate.initialize(element.getBlogPosts());
            return element;
        }).collect(Collectors.toList());
    }
}
