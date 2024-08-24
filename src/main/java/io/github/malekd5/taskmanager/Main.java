package io.github.malekd5.taskmanager;

import io.github.malekd5.taskmanager.app.ApplicationManager;
import io.github.malekd5.taskmanager.command.CommandManager;
import io.github.malekd5.taskmanager.command.CommandResult;
import io.github.malekd5.taskmanager.command.ICommand;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ApplicationManager applicationManager = new ApplicationManager();
        CommandManager commandManager = new CommandManager(applicationManager);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to TaskManager CLI");
        System.out.println("type 'help' to display list of commands.");

        while (true) {
            if (applicationManager.isLoggedIn()) {
                System.out.printf("(%s) > ", applicationManager.getLoggedInUser().getUsername());
            } else {
                System.out.print("> ");
            }
            String[] rawCommand = scanner.nextLine().split(" ");

            if (rawCommand.length == 0) {
                System.out.println("Did not receive any input, please try again.");
                continue;
            }

            String command = rawCommand[0];

            Optional<ICommand> optional = commandManager.getCommand(command);

            if (optional.isEmpty()) {
                System.out.println("Unknown command: " + command);
                continue;
            }

            ICommand dispatcher = optional.get();

            if (rawCommand.length < 2) {
                CommandResult result = dispatcher.execute(new String[] {});
                System.out.println(result.message());

                if (result.code() == -1) {
                    break;
                }
                continue;
            }

            String[] arguments = new String[rawCommand.length - 1];

            System.arraycopy(rawCommand, 1, arguments, 0, rawCommand.length - 1);

            CommandResult result = dispatcher.execute(arguments);
            System.out.println(result.message());

            if (result.code() == -1) {
                break;
            }

        }

    }

}
