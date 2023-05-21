package com.example.blog.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "blogposts")
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private int likes;

    @Column(name = "user_id", nullable = false)
    private Long authorId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User author;

    @Column(name = "topic_id", nullable = false)
    private Long topicId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", insertable = false, updatable = false)
    @JsonIgnore
    private Topic topic;

    public BlogPost(String title, String content, int likes, User user, Topic topic) {
        this.title = title;
        this.content = content;
        this.likes = likes;
        this.author = user;
        this.topic = topic;
    }

    public BlogPost(String title, String content, User user, Topic topic) {
        this.title = title;
        this.content = content;
        this.author = user;
        this.topic = topic;
    }

    BlogPost()
    {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public User getAuthor(){ return author;}

    public void setAuthor(User author){ this.author=author;}

    public void setTopic(Topic topic){this.topic=topic;}
    public Topic getTopic(){return this.topic;}

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "BlogPost{" +
                "Id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes + '\'' +
                ", author=" + author + ' ' +
                '}';
    }
}
