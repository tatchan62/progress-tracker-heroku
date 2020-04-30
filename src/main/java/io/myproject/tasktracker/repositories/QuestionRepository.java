package io.myproject.tasktracker.repositories;

import io.myproject.tasktracker.domain.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends CrudRepository<Question, Long> {

    List<Question> findByTopicIdentifierOrderByPriority(String id);

    Question findByTopicSequence(String sequence);
}
