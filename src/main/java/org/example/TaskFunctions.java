package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TaskFunctions  {

    ObjectMapper mapper = new ObjectMapper();
    Scanner scanner = new Scanner(System.in);

    File file = new File("data/tasks.json");

    List<Task> tasks = mapper.readValue(
            file, new TypeReference<List<Task>>() {}
    );

    public TaskFunctions() throws IOException {}

    public void showMainPage() throws IOException {

        while(true) {

            printAllCompleteTasks();

            System.out.println("'add' Adds new task");
            System.out.println("'edit' Edits task");
            System.out.println("'remove' Removes task");
            System.out.println("'complete' Completes task");
            System.out.println("'exit' exits program");
            System.out.println("-----------------------------");
            System.out.println("What is your choice:");

            String choice = scanner.nextLine();

            if (choice.equals("add")) {
                System.out.println("Type in the task:");
                String newTask = scanner.nextLine();
                addNewTask(newTask);

            } else if (choice.equals("edit") ||
                    choice.equals("remove") ||
                    choice.equals("complete")) {

                taskSelection(choice);

            } else if (choice.equals("exit")) {
                break;

            } else {
                System.out.println("There is no such choice.");
            }
        }
    }

    public void taskSelection(String choice) throws IOException {

        printAllCompleteTasks();

        System.out.println("Select task:");
        int selectedId = scanner.nextInt();
        scanner.nextLine();

        if(choice.equals("edit")){
            printTask(selectedId);
            System.out.println("Updated task:");
            String updatedTask = scanner.nextLine();
            editTask(selectedId, updatedTask);

        } else if(choice.equals("remove")){
            removeTask(selectedId);

        } else if(choice.equals("complete")){
            completeTask(selectedId);
        }

    }

    public void printTask(int id){
        System.out.println("-----------------------------");
        for (Task task : tasks) {
            if(task.getId()==id) {
                System.out.println(task.getName());
            }
        }
        System.out.println("-----------------------------");
    }

    public void printAllCompleteTasks(){
        System.out.println("-----------------------------");
        for (Task task : tasks) {
            if(!task.getIsComplete()) {
                System.out.println(task.getId() + ". " + task.getName());
            }
        }
        System.out.println("-----------------------------");
    }

    public void addNewTask(String name) throws IOException {

        Task newTask = new Task(
                tasks.size()+1,
                name,
                false
        );
        tasks.add(newTask);

        System.out.println("Task added..");
        saveChangesToJson();
    }

    public void editTask(int id, String name) throws IOException {

        for(Task task : tasks){
            if(task.getId()==id){
                task.setName(name);
            }
        }

        System.out.println("Task updated..");
        saveChangesToJson();
    }

    public void removeTask(int id) throws IOException {

        tasks.removeIf(task -> task.getId()==id);

        rebalanceIds(id);

        System.out.println("Task removed..");
        saveChangesToJson();
    }

    public void rebalanceIds(int id){
        for(Task task : tasks){
            if(task.getId()>id){
                task.setId(task.getId()-1);
            }
        }
    }

    public void completeTask(int id) throws IOException {

        for(Task task : tasks){
            if(task.getId()==id){
                task.setId(0);
                task.setIsComplete(true);
            }
        }

        rebalanceIds(id);

        System.out.println("Task complete..");
        saveChangesToJson();
    }

    public void saveChangesToJson() throws IOException {
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(file, tasks);
    }
}
