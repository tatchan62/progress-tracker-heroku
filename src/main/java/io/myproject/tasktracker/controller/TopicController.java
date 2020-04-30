package io.myproject.tasktracker.controller;

import io.myproject.tasktracker.domain.Topic;
import io.myproject.tasktracker.services.MapValidationErrorService;
import io.myproject.tasktracker.services.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CRUD Functions
@RestController
@RequestMapping("/api/topic")
@CrossOrigin
public class TopicController { // maintain minimal logic in Controller class

    @Autowired
    private TopicService topicService;
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    // CREATE
    @PostMapping("") // principal is the one who owns the valid token
    public ResponseEntity<?> createNewTopic(@Valid @RequestBody Topic topic, BindingResult result, Principal principal){
        // binding result determines error(from spring framework)

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null) {
            return errorMap;
        }
        Topic topic1 = topicService.saveOrUpdateTopic(topic, principal.getName());

        return new ResponseEntity<Topic>(topic1, HttpStatus.CREATED);
    }

    // READ one topic
    @GetMapping("/{topicId}")
    public ResponseEntity<?> getTopicById(@PathVariable String topicId, Principal principal) {

        Topic topic = topicService.findTopicByIdentifier(topicId, principal.getName());

        return new ResponseEntity<Topic>(topic, HttpStatus.OK);
    }

    // READ all topics
    @GetMapping("/all")
    public Iterable<Topic> getAllTopics(Principal principal) {
        return topicService.findAllTopics(principal.getName());
    }

    // DELETE one topic
    @DeleteMapping("/{topicId}")
    public ResponseEntity<?> deleteTopicById(@PathVariable String topicId, Principal principal) {

        topicService.deleteTopicByIdentifier(topicId, principal.getName());
        return new ResponseEntity<String>("Topic with ID:" + topicId + " is deleted", HttpStatus.OK);
    }
}
