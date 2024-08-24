package io.github.malekd5.taskmanager.command.impl;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.auth.User;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

public class LogoutCommand implements ICommand {

    private final ApplicationManager manager;

    public LogoutCommand(ApplicationManager manager) {
        this.manager = manager;
    }

    @Override
    public String name() {
        return "logout";
    }

    @Override
    public boolean canExecute() {
        return this.manager.isLoggedIn();
    }

    @Override
    public CommandResult execute(String[] args) {
        User user = this.manager.getLoggedInUser();

        String name = user.getUsername();

        manager.logout();
        return CommandResult.ok(String.format("Goodbye %s, come back again!", name));
    }
}
