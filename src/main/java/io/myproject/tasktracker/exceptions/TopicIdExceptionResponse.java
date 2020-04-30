package io.myproject.tasktracker.exceptions;

// Set up to return a JSON object error with <key : value>
public class TopicIdExceptionResponse {

    private String topicIdentifier;

    public TopicIdExceptionResponse(String topicIdentifier) {
        this.topicIdentifier = topicIdentifier;
    }

    public String getTopicIdentifier() {
        return topicIdentifier;
    }

    public void setTopicIdentifier(String topicIdentifier) {
        this.topicIdentifier = topicIdentifier;
    }
}
