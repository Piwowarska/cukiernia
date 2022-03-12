package pl.camp.it.cake.validators;

import pl.camp.it.cake.exceptions.ValidationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    public static void validateLogin(String login) {
        if (!login.matches(".{3}.*")) {
            throw new ValidationException("Login is too short");
        }
    }

    public static void validatePassword(String password) {
        if (!password.matches(".{3}.*")) {
            throw new ValidationException("Password is to short");
        }
    }

    public static void validateName(String name) {
        basicValidation(name);
    }

    public static void validateSurname(String surname) {
        basicValidation(surname);
    }

    public static void validateMail(String mail) {
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]{2,3}");
        Matcher matcher = pattern.matcher(mail);
        if (!matcher.matches()) {
            throw new ValidationException("Incorrect mail");
        }
    }

    public static void validatePasswordsEquality(String pass1, String pass2) {
        if (!pass1.equals(pass2)) {
            throw new ValidationException("passwords are not equal");
        }
    }

    private static void basicValidation(String value) {
        if (!value.matches("[A-Z][a-z]+")) {
            throw new ValidationException("value incorrect");
        }
    }
}
