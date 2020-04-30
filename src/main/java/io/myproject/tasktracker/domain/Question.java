package io.myproject.tasktracker.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, unique = true)
    private String topicSequence;  // variable to search individual questions

    @NotBlank(message = "Please include Leetcode question no. and its title")
    private String summary;

    // ManyToOne with backlog
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    private Backlog backlog;

    private String note;
    private String status;
    private Integer priority;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dueDate;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date create_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date update_At;

    // ManyToOne with Backlog
    @Column(updatable = false)
    private String topicIdentifier;

    @PrePersist
    protected void onCreate(){
        this.create_At = new Date();
    }

    @PreUpdate
    protected  void onUpdate() {
        this.update_At = new Date();
    }

    public Question() {
    }

    public Backlog getBacklog() {
        return backlog;
    }

    public void setBacklog(Backlog backlog) {
        this.backlog = backlog;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopicSequence() {
        return topicSequence;
    }

    public void setTopicSequence(String topicSequence) {
        this.topicSequence = topicSequence;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getCreate_At() {
        return create_At;
    }

    public void setCreate_At(Date create_At) {
        this.create_At = create_At;
    }

    public Date getUpdate_At() {
        return update_At;
    }

    public void setUpdate_At(Date update_At) {
        this.update_At = update_At;
    }

    public String getTopicIdentifier() {
        return topicIdentifier;
    }

    public void setTopicIdentifier(String topicIdentifier) {
        this.topicIdentifier = topicIdentifier;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", topicSequence='" + topicSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", create_At=" + create_At +
                ", update_At=" + update_At +
                ", topicIdentifier='" + topicIdentifier + '\'' +
                '}';
    }
}
