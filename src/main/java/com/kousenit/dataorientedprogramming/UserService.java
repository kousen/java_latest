package com.kousenit.dataorientedprogramming;

import static com.kousenit.dataorientedprogramming.UserRecords.*;

public class UserService {
    public static String getPersonInfo(Person person) {
        // Exhaustive switch expression because Person is sealed
        return switch (person) {
            case User user -> "User: %s (%s)".formatted(user.name(), user.email());
            case Admin admin -> "Admin: %s (%s) with permissions: %s"
                            .formatted(admin.name(), admin.email(), admin.permissions());
        };
    }

    public static Person updateEmail(Person person, String newEmail) {
        // Records are immutable, so we have to create a new one
        return switch (person) {
            case User user -> new User(user.id(), user.name(), newEmail);
            case Admin admin -> new Admin(admin.id(), admin.name(), newEmail, admin.permissions());
        };
    }
}
