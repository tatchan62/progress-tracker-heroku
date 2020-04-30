package io.myproject.tasktracker.repositories;

import io.myproject.tasktracker.domain.Topic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends CrudRepository<Topic, Long> {

    Topic findByTopicIdentifier(String topicIdentifier);

    @Override
    Iterable<Topic> findAll();

    Iterable<Topic> findAllByTopicLeader(String username);


}
