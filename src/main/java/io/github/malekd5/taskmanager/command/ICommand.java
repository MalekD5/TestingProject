package io.github.malekd5.taskmanager.command;

public interface ICommand {

    String name();

    CommandResult execute(String[] args);

    default boolean hasArguments() {
        return false;
    }

    default boolean canExecute() {
        return true;
    }

}
