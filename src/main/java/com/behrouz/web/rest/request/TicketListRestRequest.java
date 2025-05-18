package com.behrouz.web.rest.request;


/**
 * Created by: HapiKZM
 **/
public class TicketListRestRequest {

    private int importance;
    private int project;
    private String search;

    public TicketListRestRequest( int importance, int project, String search) {
        this.importance = importance;
        this.project = project;
        this.search = search;
    }

    public TicketListRestRequest() {
    }

    public int getImportance() {
        return importance;
    }
    public void setImportance(int importance) {
        this.importance = importance;
    }

    public int getProject() {
        return project;
    }
    public void setProject(int project) {
        this.project = project;
    }

    public String getSearch() {
        return search;
    }
    public void setSearch(String search) {
        this.search = search;
    }
}
