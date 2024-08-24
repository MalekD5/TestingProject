package io.github.malekd5.taskmanager.command.impl.task;

import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

public class TaskHelpSubCommand implements ICommand {

    private final TaskMainCommand taskMainCommand;

    public TaskHelpSubCommand(TaskMainCommand taskMainCommand) {
        this.taskMainCommand = taskMainCommand;
    }

    /**
     * DO NOT USE THIS VALUE FOR REGISTERING SUB COMMANDS
     * @return fake command name, do not use!!
     */
    @Override
    public String name() {
        return "task help";
    }

    @Override
    public CommandResult execute(String[] args) {
        var commands = this.taskMainCommand.getSubCommands();

        System.out.println("List of available task commands: ");

        commands.forEach((__, cmd) -> System.out.println(" - " + cmd.name()));

        return CommandResult.ok();
    }
}
