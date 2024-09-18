package io.github.malekd5.taskmanager.command;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.impl.*;
import io.github.malekd5.taskmanager.command.impl.task.TaskMainCommand;

import java.util.*;

public class CommandManager {

    private final Map<String, ICommand> commandMap;

    public CommandManager(ApplicationManager manager) {
        this.commandMap = new HashMap<>();

        this.commandMap.put("exit", new ExitCommand());
        this.commandMap.put("help", new HelpCommand(this));
        this.commandMap.put("login", new LoginCommand(manager));
        this.commandMap.put("register", new RegisterCommand(manager));
        this.commandMap.put("task", new TaskMainCommand(manager));
    }

    public Optional<ICommand> getCommand(String command) {
        ICommand cmd = commandMap.get(command);

        if (cmd != null && cmd.canExecute()) {
            return Optional.of(cmd);
        }

        return Optional.empty();
    }

    /**
     * There are some commands that can't be executed depending on the state
     * of the application. such as login and register.
     *
     * @return commands that can be executed
     */
    public List<ICommand> getExecutableCommands() {
        return commandMap
                .values()
                .stream()
                .filter(ICommand::canExecute)
                .toList();
    }

}
