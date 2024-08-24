package io.github.malekd5.taskmanager.command.impl;

import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

public class ExitCommand implements ICommand {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public CommandResult execute(String[] args) {
        return CommandResult.exit();
    }

}
