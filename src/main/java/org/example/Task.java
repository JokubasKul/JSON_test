package org.example;

public class Task {

    private int id;
    private String task;
    private String colour;
    private boolean isComplete;

    public Task() {}

    public Task(int id, String task, String colour, boolean isComplete){
        this.id=id;
        this.task=task;
        this.colour=colour;
        this.isComplete=isComplete;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }
    public void setTask(String task) {
        this.task = task;
    }

    public String getColour() {
        return colour;
    }
    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean getIsComplete() {
        return isComplete;
    }
    public void setIsComplete(boolean complete) {
        this.isComplete = complete;
    }
}
