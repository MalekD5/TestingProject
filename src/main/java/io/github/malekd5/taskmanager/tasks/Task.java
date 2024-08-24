package io.github.malekd5.taskmanager.tasks;

public class Task {

    private String title;
    private String description;
    private String category;
    private int priority;
    private boolean completed;

    public Task(String title, String description, String category, int priority) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.priority = priority;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %d | %s", title, description , category, priority, completed);
    }
}
