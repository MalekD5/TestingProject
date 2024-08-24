package io.github.malekd5.taskmanager.utils;

public class ValidationUtils {

    public static boolean isValidUsername(String username) {
        return username != null && username.length() >= 5;
    }

    public static boolean isValidPassword(String password) {
        return password != null && password.length() >= 6;
    }

}
