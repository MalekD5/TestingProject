package io.github.malekd5.taskmanager.auth;

import io.github.malekd5.taskmanager.exceptions.DuplicateUsernameException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AuthService {

    private final Map<String, User> users = new HashMap<>();

    public void registerUser(String username, String password) {
        if (users.containsKey(username)) {
            throw new DuplicateUsernameException("Username already exists.");
        }
        users.put(username, new User(username, password));
    }

    public Optional<User> loginUser(String username, String password) {
        User user = users.get(username);
        if (user != null && user.password().equals(password)) {
            return Optional.of(user);
        }

        return Optional.empty();
    }

}
