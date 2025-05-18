package com.behrouz.web.rest.request;

public class TicketDetailRestRequest {


    private long id;
    private long projectId;
    private String Subject;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }


    public long getProjectId() {
        return projectId;
    }
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }


    public String getSubject() {
        return Subject;
    }
    public void setSubject(String subject) {
        Subject = subject;
    }
}
