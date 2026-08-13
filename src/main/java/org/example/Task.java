package org.example;

public class Task {

    private int id;
    private String name;
    private boolean isComplete;

    public Task() {}

    public Task(int id, String name, boolean isComplete){
        this.id=id;
        this.name=name;
        this.isComplete=isComplete;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsComplete() {
        return isComplete;
    }
    public void setIsComplete(boolean complete) {
        this.isComplete = complete;
    }
}
