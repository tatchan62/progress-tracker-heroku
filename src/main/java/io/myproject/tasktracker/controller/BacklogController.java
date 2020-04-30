package io.myproject.tasktracker.controller;

import io.myproject.tasktracker.domain.Question;
import io.myproject.tasktracker.services.MapValidationErrorService;
import io.myproject.tasktracker.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addQuestionToBacklog(@Valid @RequestBody Question question,
                                                  BindingResult result, @PathVariable String backlog_id,
                                                  Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null){
            return errorMap;
        }

        Question question1 = questionService.addQuestion(backlog_id, question, principal.getName());
        return new ResponseEntity<Question>(question1, HttpStatus.CREATED);
    }

    // return a list of questions of a topic
    @GetMapping("/{backlog_id}")
    public Iterable<Question> getTopicBacklog(@PathVariable String backlog_id, Principal principal){

        return questionService.findBacklogById(backlog_id, principal.getName());
    }

    // q_id is the topic sequence. Ex. ARR-1
    @GetMapping("/{backlog_id}/{q_id}")
    public ResponseEntity<?> getQuestion(@PathVariable String backlog_id, @PathVariable String q_id, Principal principal){
        Question question = questionService.findPTByTopicSequence(backlog_id, q_id, principal.getName());
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }

    // update details of a question
    @PatchMapping("/{backlog_id}/{q_id}")
    public ResponseEntity<?> updateQuestion(@Valid @RequestBody Question question, BindingResult result,
                                            @PathVariable String backlog_id, @PathVariable String q_id,
                                            Principal principal){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if (errorMap != null){
            return errorMap;
        }

        Question updatedQuestion = questionService.updateByTopicSequence(question, backlog_id, q_id, principal.getName());

        return new ResponseEntity<Question>(updatedQuestion, HttpStatus.OK);
    }

    // Delete a question
    @DeleteMapping("/{backlog_id}/{q_id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable String backlog_id, @PathVariable String q_id,
                                            Principal principal){

        questionService.deleteQuestionByTopicSequence(backlog_id, q_id, principal.getName());
        return new ResponseEntity<String>("Question " + q_id + " was successfully deleted", HttpStatus.OK);
    }
}
