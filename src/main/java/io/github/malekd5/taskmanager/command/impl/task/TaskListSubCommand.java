package io.github.malekd5.taskmanager.command.impl.task;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;
import io.github.malekd5.taskmanager.tasks.Task;
import io.github.malekd5.taskmanager.tasks.TaskManager;

import java.util.List;

public class TaskListSubCommand implements ICommand {

    private final ApplicationManager applicationManager;

    public TaskListSubCommand(final ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override
    public String name() {
        return "task list";
    }

    @Override
    public CommandResult execute(String[] args) {
        int flag = -1;
        String type = "";

        if (args.length == 2) {
            String rawFlag = args[0];
            type = args[1];

            flag = switch (rawFlag) {
                case "category" -> 1;
                case "priority" -> 2;
                default -> flag;
            };
        }

        var optionalTaskManager = this.applicationManager.getTaskManager();

        if (optionalTaskManager.isEmpty()) {
            return CommandResult.error("unauthorized");
        }

        TaskManager taskManager = optionalTaskManager.get();

        List<Task> tasks = switch (flag) {
            case 1 -> taskManager.getTasksByCategory(type);
            case 2 -> {
                int priority = Integer.parseInt(type);
                yield taskManager.getTasksByPriority(priority);
            }
            default -> taskManager.getTasks();
        };

        System.out.println("List of tasks: ");
        tasks.forEach(System.out::println);

        return CommandResult.ok();
    }
}
