package io.github.malekd5.taskmanager.command.impl;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

public class LoginCommand implements ICommand {

    private final ApplicationManager manager;

    public LoginCommand(ApplicationManager manager) {
        this.manager = manager;
    }

    @Override
    public String name() {
        return "login";
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length != 2) {
            return CommandResult.error("Invalid arguments: login <username> <password>");
        }

        String username = args[0];
        String password = args[1];

        try {
            this.manager.login(username, password);
            return CommandResult.ok("Welcome " + username + "! type 'help' to show available commands!");
        } catch (IllegalArgumentException e) {
            return CommandResult.error("Invalid username or password");
        }
    }

    @Override
    public boolean canExecute() {
        return !manager.isLoggedIn();
    }
}
