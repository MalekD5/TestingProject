package io.github.malekd5.taskmanager.command.impl.task;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

import java.util.HashMap;
import java.util.function.Supplier;

public class TaskMainCommand implements ICommand {

    private final HashMap<String, ICommand> subCommands;

    private final ApplicationManager manager;

    public TaskMainCommand(ApplicationManager manager) {
        this.manager = manager;
        this.subCommands = new HashMap<>();

        this.subCommands.put("help",
                new TaskHelpSubCommand(this));
        this.subCommands.put("create", new CreateTaskSubCommand(manager));
        this.subCommands.put("delete", new DeleteTaskSubCommand(manager));
        this.subCommands.put("list", new TaskListSubCommand(manager));
        this.subCommands.put("complete", new CompleteTaskSubCommand(manager));
    }

    @Override
    public String name() {
        return "task";
    }

    @Override
    public boolean canExecute() {
        return manager.isLoggedIn();
    }

    @Override
    public CommandResult execute(String[] args) {
        if (args.length == 0) {
            return this.help().get();
        }

        String cmd = args[0];

        var subCommand = subCommands.get(cmd);
        if (subCommand == null) {
            return this.help().get();
        }

        String[] subCommandArgs = new String[args.length - 1];
        if (subCommandArgs.length == 0) {
            if (subCommand.hasArguments())
                return CommandResult.error("invalid arguments: " + subCommand.name());

            return subCommand.execute(new String[] {});
        }

        System.arraycopy(args, 1, subCommandArgs, 0, subCommandArgs.length);

        return subCommand.execute(subCommandArgs);
    }

    public HashMap<String, ICommand> getSubCommands() {
        return subCommands;
    }

    private Supplier<CommandResult> help() {
        return () -> this.subCommands.get("help").execute(new String[] {});
    }
}
