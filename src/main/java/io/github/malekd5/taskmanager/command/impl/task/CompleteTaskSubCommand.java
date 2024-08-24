package io.github.malekd5.taskmanager.command.impl.task;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;
import io.github.malekd5.taskmanager.tasks.Task;
import io.github.malekd5.taskmanager.tasks.TaskManager;

public class CompleteTaskSubCommand implements ICommand {

    private final ApplicationManager manager;

    public CompleteTaskSubCommand(final ApplicationManager manager) {
        this.manager = manager;
    }

    @Override
    public String name() {
        return "task complete <name>";
    }

    @Override
    public CommandResult execute(String[] args) {

        if (args.length != 1) {
            return CommandResult.error("invalid arguments: " + this.name());
        }

        String name = args[0];

        var optionalTaskManager = manager.getTaskManager();

        if (optionalTaskManager.isEmpty()) {
            return CommandResult.error("unauthorized");
        }

        TaskManager taskManager = optionalTaskManager.get();

        var taskOptional = taskManager.getTask(name);

        if (taskOptional.isEmpty()) {
            return CommandResult.error(String.format("'%s' Task not found", name));
        }

        Task task = taskOptional.get();

        task.markAsCompleted();

        return CommandResult.ok(String.format("'%s' Task marked as complete", name));
    }
}
