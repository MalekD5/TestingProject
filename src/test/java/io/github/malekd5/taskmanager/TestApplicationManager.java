package io.github.malekd5.taskmanager;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import io.github.malekd5.taskmanager.exceptions.DuplicateUsernameException;
import io.github.malekd5.taskmanager.exceptions.InvalidPasswordException;
import io.github.malekd5.taskmanager.exceptions.InvalidUsernameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.malekd5.taskmanager.app.ApplicationManager;

public class TestApplicationManager {

    private ApplicationManager applicationManager ;

    @BeforeEach
    public void setup() {
        applicationManager = new ApplicationManager();
    }

    @Test
    public void testRegisterShouldSucceed() {
        assertDoesNotThrow(() ->
                applicationManager.register("malek", "123456"));
    }

    @Test
    public void testRegisterShouldThrow() {

        applicationManager.register("malek", "123456");
        assertThrows(DuplicateUsernameException.class,
                () -> applicationManager.register("malek", "123456"));
    }

    @Test
    public void testRegisterShouldFailInvalidUserName() {

        assertThrows(InvalidUsernameException.class,
                () -> applicationManager.register("male", "123456"));
    }
    @Test
    public void testRegisterShouldFailInvalidPassword() {

        assertThrows(InvalidPasswordException.class,
                () -> applicationManager.register("malek", "12345"));
    }

    @Test
    public void testLoginShouldSucceed() {
        applicationManager.register("malek", "123456");
        assertDoesNotThrow(() ->
                applicationManager.login("malek", "123456"));

    }

    @Test
    public void testLoginShouldFailAlreadyLoggedIn() {
        applicationManager.register("malek", "123456");
        applicationManager.login("malek", "123456");
        assertThrows(IllegalStateException.class,
                () -> applicationManager.login("malek", "123456"));
    }

    @Test
    public void testLoginShouldFailInCorrectUsername() {
        applicationManager.register("malek", "123456");
        assertThrows(IllegalArgumentException.class,
                () -> applicationManager.login("mallk", "12345"));
    }

    @Test
    public void testLoginShouldFailInCorrectPassword() {
        applicationManager.register("malek", "123456");
        assertThrows(IllegalArgumentException.class,
                () -> applicationManager.login("malek", "12355"));
    }

}