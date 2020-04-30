package io.myproject.tasktracker.exceptions;

public class TopicNotFoundExceptionResponse {

    private String TopicNotFound;

    public TopicNotFoundExceptionResponse(String topicNotFound) {
        TopicNotFound = topicNotFound;
    }

    public String getTopicNotFound() {
        return TopicNotFound;
    }

    public void setTopicNotFound(String topicNotFound) {
        TopicNotFound = topicNotFound;
    }
}
