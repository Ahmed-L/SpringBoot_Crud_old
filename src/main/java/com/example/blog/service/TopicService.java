package com.example.blog.service;

import com.example.blog.model.Topic;
import com.example.blog.repository.TopicRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class TopicService {
    private TopicRepository topicRepository;
    private EntityManager entityManager;

    TopicService(TopicRepository e, EntityManager entityManager)
    {
        this.topicRepository = e;
        this.entityManager = entityManager;
    }

    public List<Topic> getAllTopic()
    {
        return topicRepository.findAll();
    }

    public Topic getTopicById(Long id)
    {
        return topicRepository.findById(id).get();
    }

    public Topic updateTopicById(Long id, Topic topic)
    {
        Topic topicToBeUpdated = topicRepository.findById(id).get();
        if(topic.getName()!=null)
            topicToBeUpdated.setName(topic.getName());
        if(topic.getType()!=null)
            topicToBeUpdated.setType(topic.getType());
        if(topic.getBlogPosts()!=null)
            topicToBeUpdated.setBlogPosts(topic.getBlogPosts());
        return topic;
    }

    public Topic createTopic(Topic topic)
    {
        return topicRepository.save(topic);
    }
    public String deleteTopic(Long id)
    {
        topicRepository.deleteById(id);
        return "Successfully deleted topic.";
    }

    public List<Topic> findTopicsWithBlogPosts() {
        String jpql = "SELECT t FROM Topic t LEFT JOIN FETCH t.blogPosts";
        TypedQuery<Topic> query = entityManager.createQuery(jpql, Topic.class);
        return query.getResultList();
    }
}
