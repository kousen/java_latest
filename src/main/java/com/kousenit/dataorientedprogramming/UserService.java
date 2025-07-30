package com.kousenit.dataorientedprogramming;

import static com.kousenit.dataorientedprogramming.UserRecords.*;

public class UserService {
    public static String getPersonInfo(Person person) {
        // Exhaustive switch expression with record patterns (Java 21)
        return switch (person) {
            case User(var id, var name, var email) -> "User: %s (%s)".formatted(name, email);
            case Admin(var id, var name, var email, var permissions) -> "Admin: %s (%s) with permissions: %s"
                            .formatted(name, email, permissions);
        };
    }

    public static Person updateEmail(Person person, String newEmail) {
        // Records are immutable, so we have to create a new one with record patterns
        return switch (person) {
            case User(var id, var name, var email) -> new User(id, name, newEmail);
            case Admin(var id, var name, var email, var permissions) -> new Admin(id, name, newEmail, permissions);
        };
    }
}
