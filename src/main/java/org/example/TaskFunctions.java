package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TaskFunctions {

    ObjectMapper mapper = new ObjectMapper();

    File file = new File("data/tasks.json");

    List<Task> tasks = mapper.readValue(
            file, new TypeReference<List<Task>>() {}
    );

    public TaskFunctions() throws IOException {
    }

    public void printTasks(){
        for (Task task : tasks) {
            System.out.println(task.getId() + ". " + task.getTask());
        }
    }
}
