package io.github.malekd5.taskmanager.command.impl.task;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;
import io.github.malekd5.taskmanager.tasks.Task;

import java.util.Optional;

public class CreateTaskSubCommand implements ICommand {

    private final ApplicationManager applicationManager;

    public CreateTaskSubCommand(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public String name() {
        return "task create <name> <description> <category> <priority>";
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 4) {
            return CommandResult.error("invalid arguments: " + this.name());
        }

        String name = args[0];
        String description = args[1];
        String category = args[2];
        int priority;

        try {
            priority = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            return CommandResult.error("priority must be a number");
        }

        var optionalTaskManager = this.applicationManager.getTaskManager();

        if (optionalTaskManager.isEmpty()) {
            return CommandResult.error("unauthorized");
        }

        var taskManager = optionalTaskManager.get();

        Task task = new Task(name, description, category, priority);
        taskManager.addTask(task);

        return CommandResult.ok("task created!");
    }
}
