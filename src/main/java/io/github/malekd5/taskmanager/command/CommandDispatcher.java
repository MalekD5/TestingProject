package io.github.malekd5.taskmanager.command;

import io.github.malekd5.taskmanager.app.ApplicationManager;

public class CommandDispatcher {

    private final CommandManager commandManager;

    public CommandDispatcher(ApplicationManager applicationManager) {
        this.commandManager = new CommandManager(applicationManager);
    }

}
