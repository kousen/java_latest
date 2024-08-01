package com.kousenit.dataorientedprogramming;

import org.junit.jupiter.api.Test;

import static com.kousenit.dataorientedprogramming.UserRecords.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    @Test
    void testGetPersonInfo() {
        Person user = new User(1, "John Doe", "john@example.com");
        Person admin = new Admin(2, "Jane Smith",
                "jane@example.com", "ALL");

        assertThat(UserService.getPersonInfo(user))
                .isEqualTo("User: John Doe (john@example.com)");
        assertThat(UserService.getPersonInfo(admin))
                .isEqualTo("Admin: Jane Smith (jane@example.com) with permissions: ALL");

        Person updatedUser = UserService.updateEmail(user, "john.doe@example.com");
        Person updatedAdmin = UserService.updateEmail(admin, "jane.smith@example.com");

        assertThat(UserService.getPersonInfo(updatedUser))
                .isEqualTo("User: John Doe (john.doe@example.com)");
        assertThat(UserService.getPersonInfo(updatedAdmin))
                .isEqualTo("Admin: Jane Smith (jane.smith@example.com) with permissions: ALL");
    }
}