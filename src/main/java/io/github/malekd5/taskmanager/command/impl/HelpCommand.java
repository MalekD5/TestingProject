package io.github.malekd5.taskmanager.command.impl;

import io.github.malekd5.taskmanager.command.CommandManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

public class HelpCommand implements ICommand {

    private final CommandManager manager;

    public HelpCommand(CommandManager manager) {
        this.manager = manager;
    }

    @Override
    public String name() {
        return "help";
    }

    @Override
    public CommandResult execute(String[] args) {
        var commands = manager.getExecutableCommands();

        System.out.println("List of available commands: ");

        commands.forEach(cmd -> System.out.println(" - " + cmd.name()));

        return CommandResult.ok();
    }
}

