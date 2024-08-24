package io.github.malekd5.taskmanager.command.impl.task;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;
import io.github.malekd5.taskmanager.tasks.Task;
import io.github.malekd5.taskmanager.tasks.TaskManager;

public class DeleteTaskSubCommand implements ICommand {

    private final ApplicationManager applicationManager;

    public DeleteTaskSubCommand(final ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public String name() {
        return "task delete <name>";
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 1) {
            return CommandResult.error("invalid arguments: " + this.name());
        }

        String name = args[0];

        var optionalTaskManager = this.applicationManager.getTaskManager();

        if (optionalTaskManager.isEmpty()) {
            return CommandResult.error("unauthorized");
        }

        TaskManager taskManager = optionalTaskManager.get();

        var taskOptional = taskManager.getTask(name);

        if (taskOptional.isEmpty()) {
            return CommandResult.error("task does not exist");
        }

        Task task = taskOptional.get();

        if (!taskManager.deleteTask(task)) {
            return CommandResult.error("task does not exist");
        }

        return CommandResult.ok(String.format("task '%s' deleted!", name));
    }
}
