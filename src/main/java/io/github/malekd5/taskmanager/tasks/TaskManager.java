package io.github.malekd5.taskmanager.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskManager {

    private final List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Optional<Task> getTask(String name) {
        return tasks.stream().filter(t -> t.getTitle()
                .equalsIgnoreCase(name)).findFirst();
    }

    public boolean deleteTask(Task task) {
        return tasks.remove(task);
    }

    public List<Task> getTasksByCategory(String category) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getCategory().equalsIgnoreCase(category)) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public List<Task> getTasksByPriority(int priority) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getPriority() == priority) {
                filteredTasks.add(task);
            }
        }
        return filteredTasks;
    }

    public void markTaskAsCompleted(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markAsCompleted();
                return;
            }
        }
        throw new IllegalArgumentException("Task not found.");
    }

}
