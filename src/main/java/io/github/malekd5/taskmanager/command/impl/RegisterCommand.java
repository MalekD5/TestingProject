package io.github.malekd5.taskmanager.command.impl;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

public class RegisterCommand implements ICommand {

    private final ApplicationManager manager;

    public RegisterCommand(ApplicationManager manager) {
        this.manager = manager;
    }

    @Override
    public String name() {
        return "register";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 2) {
            return CommandResult.error("Invalid arguments: register <username> <password>");
        }

        String username = args[0];
        String password = args[1];

        try {
            this.manager.register(username, password);
            return CommandResult.ok("Register successful, please login now");
        } catch (IllegalArgumentException e) {
            return CommandResult.error(e.getMessage());
        }
    }

    @Override
    public boolean canExecute() {
        return !this.manager.isLoggedIn();
    }
}
