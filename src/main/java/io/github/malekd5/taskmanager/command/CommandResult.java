package io.github.malekd5.taskmanager.command;

public record CommandResult(int code, String message) {

    public static CommandResult exit() {
        return new CommandResult(-1, "Goodbye! come back again");
    }

    public static CommandResult ok(String message) {
        return new CommandResult(1, message);
    }

    public static CommandResult error(String message) {
        return new CommandResult(0, message);
    }

    public static CommandResult ok() {
        return ok("");
    }

    public String message() {
        if (message == null || message.isEmpty())
            return "";

        if (this.code() == 0) {
            return "(ERR): " + this.message;
        } else {
            return "(OK): " + this.message;
        }
    }

}
