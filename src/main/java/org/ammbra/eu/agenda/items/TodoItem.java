package org.ammbra.eu.agenda.items;


import java.time.LocalDate;

/// a `TodoItem` must hold a title, description, priority, creation date and deadline;
public class TodoItem {

    private String title;
    private String description;
    private String priority;
    private LocalDate createdOn;
    private LocalDate deadline;

    public TodoItem(String title, String description, String priority, LocalDate createdOn, LocalDate deadline) {
        this.createdOn = createdOn;
        this.deadline = deadline;
        this.description = description;
        this.priority = priority;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }



    
}
