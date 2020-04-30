package io.myproject.tasktracker.repositories;

import io.myproject.tasktracker.domain.Backlog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BacklogRepository extends CrudRepository<Backlog, Long> {

    Backlog findByTopicIdentifier(String Identifier);
}
