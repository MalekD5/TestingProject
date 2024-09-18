package io.github.malekd5.taskmanager.app;

import io.github.malekd5.taskmanager.auth.AuthService;
import io.github.malekd5.taskmanager.auth.User;
import io.github.malekd5.taskmanager.exceptions.InvalidPasswordException;
import io.github.malekd5.taskmanager.exceptions.InvalidUsernameException;
import io.github.malekd5.taskmanager.tasks.TaskManager;
import io.github.malekd5.taskmanager.utils.ValidationUtils;

import java.util.Optional;

public class ApplicationManager {

    private final AuthService authService;

    private TaskManager taskManager;

    private User loggedInUser;

    public ApplicationManager() {
        this.authService = new AuthService();
    }

    public void register(String username, String password) {
        if (!ValidationUtils.isValidUsername(username) ) {
            throw new InvalidUsernameException("Username must be at least 5 characters");
        }

        if (!ValidationUtils.isValidPassword(password)) {
            throw new InvalidPasswordException("Password must be at least 6 characters");
        }

        this.authService.registerUser(username, password);
    }

    public void login(String username, String password) {
        if (isLoggedIn()) {
            throw new IllegalStateException("already logged in");
        }

        Optional<User> optionalUser = this.authService.loginUser(username, password);

        if (optionalUser.isEmpty()) {
           throw new IllegalArgumentException("Invalid username/password");
        }

        this.loggedInUser = optionalUser.get();
        this.taskManager = new TaskManager();
    }

    public void logout() {
        this.loggedInUser = null;
        this.taskManager = null;
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public Optional<TaskManager> getTaskManager() {
        return Optional.ofNullable(this.taskManager);
    }

}
