package io.myproject.tasktracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TopicIdException extends  RuntimeException{

    public TopicIdException(String message) {
        super(message);
    }
}
