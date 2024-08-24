package io.github.malekd5.taskmanager;

import io.github.malekd5.taskmanager.auth.AuthService;
import io.github.malekd5.taskmanager.auth.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class TestAuthService {

    private static AuthService authService;

    @BeforeAll
    public static void setupOnce() {
        authService = new AuthService();
    }

    @Test
    public void testRegisterShouldSucceed() {
        assertDoesNotThrow(() ->
                authService.registerUser("malek", "123456"));
    }

    @Test
    public void testRegisterShouldThrow() {
        assertThrows(IllegalArgumentException.class,
                () -> authService.registerUser("malek", "123456"));
    }

    @Test
    public void testLoginShouldSucceed() {
        Optional<User> optionalUser = authService.loginUser("malek",
                "123456");

        assertTrue(optionalUser.isPresent());
    }

    @Test
    public void testLoginShouldFailNotFound() {
        Optional<User> optionalUser = authService.loginUser("test",
                "123456");

        assertFalse(optionalUser.isPresent());
    }

    @Test
    public void testLoginShouldFailIncorrectPassword() {
        Optional<User> optionalUser = authService.loginUser("malek",
                "1234567");

        assertFalse(optionalUser.isPresent());
    }

}
