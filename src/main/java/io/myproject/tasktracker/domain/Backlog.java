package io.myproject.tasktracker.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// This Backlog class serves as a coupling between Topic and Question
@Entity
public class Backlog {
    // 1 Topic matches to only 1 backlog 1 : 1
    // 1 backlog matches to many questions 1 : Many

    //OneToOne with Topic
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    @JsonIgnore // set up at child side to prevent JSON infinite recursive calls
    private Topic topic;

    // OneToMany questions
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "backlog", orphanRemoval = true)
    private List<Question> questions = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer PTSequence = 0;
    private String topicIdentifier;

    public Backlog() {
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPTSequence() {
        return PTSequence;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public String getTopicIdentifier() {
        return topicIdentifier;
    }

    public void setTopicIdentifier(String topicIdentifier) {
        this.topicIdentifier = topicIdentifier;
    }
}
