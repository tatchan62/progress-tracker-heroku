package io.myproject.tasktracker.services;

import io.myproject.tasktracker.domain.Backlog;
import io.myproject.tasktracker.domain.Topic;
import io.myproject.tasktracker.domain.User;
import io.myproject.tasktracker.exceptions.TopicIdException;
import io.myproject.tasktracker.exceptions.TopicNotFoundException;
import io.myproject.tasktracker.repositories.BacklogRepository;
import io.myproject.tasktracker.repositories.TopicRepository;
import io.myproject.tasktracker.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private UserRepository userRepository;

    // Save or update a Topic(share with CREATE and UPDATE)
    public Topic saveOrUpdateTopic(Topic topic, String username) {

            if (topic.getId() != null){
                Topic existingTopic = topicRepository.findByTopicIdentifier(topic.getTopicIdentifier());

                // the topic exists but the owner of this topic doesn't match the provided user
                if (existingTopic != null && (!existingTopic.getTopicLeader().equals(username))){
                    throw new TopicNotFoundException("Topic not found in your account");
                }
                else if (existingTopic == null){
                    throw new TopicNotFoundException("Topic with ID '" + topic.getTopicIdentifier() +
                            "' can't be updated because it doesn't exist");
                }
            }

            try {
            // Get the user
            User user = userRepository.findByUsername(username);
            // set this topic with the provided user
            topic.setUser(user);
            topic.setTopicLeader(user.getUsername());
            topic.setTopicIdentifier(topic.getTopicIdentifier().toUpperCase());

            // it's a New Topic
            if (topic.getId() == null){

                // Create a new Backlog for the new topic
                Backlog backlog = new Backlog();
                topic.setBacklog(backlog);
                backlog.setTopic(topic);
                backlog.setTopicIdentifier(topic.getTopicIdentifier().toUpperCase());
            }

            // Updating a Topic. Get the topic's existing backlog with its identifier from BacklogRepository
            if (topic.getId() != null) {
                topic.setBacklog(backlogRepository.findByTopicIdentifier(topic.getTopicIdentifier().toUpperCase()));
            }

            return topicRepository.save(topic);
        }
        catch (Exception e) {
            throw new TopicIdException("Topic ID: " + topic.getTopicIdentifier().toUpperCase() + " already exists");
        }
    }

    // locate a topic
    public Topic findTopicByIdentifier(String topicId, String username) {

        // Get topic
        Topic topic = topicRepository.findByTopicIdentifier(topicId.toUpperCase());

        if (topic == null) {
            throw new TopicIdException("Topic ID: " + topicId + " doesn't exist");
        }

        // if the topic is not belonged to this username
        if(!topic.getTopicLeader().equals(username)){
            throw new TopicNotFoundException("Topic not found in your account");
        }

        return topic;
    }

    // Get all topics
    public Iterable<Topic> findAllTopics(String username) {

        return topicRepository.findAllByTopicLeader(username);
    }

    // Delete
    public void deleteTopicByIdentifier(String topicId, String username) {

       topicRepository.delete(findTopicByIdentifier(topicId, username));

    }
}
