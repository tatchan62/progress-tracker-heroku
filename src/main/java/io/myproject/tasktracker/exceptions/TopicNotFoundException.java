package io.myproject.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TopicNotFoundException extends RuntimeException{

    public TopicNotFoundException(String message) {
        super(message);
    }
}
