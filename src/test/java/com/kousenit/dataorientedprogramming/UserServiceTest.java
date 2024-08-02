package com.kousenit.dataorientedprogramming;

import org.junit.jupiter.api.Test;

import static com.kousenit.dataorientedprogramming.UserRecords.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class UserServiceTest {

    @Test
    void testGetPersonInfo() {
        Person user = new User(1, "John Doe", "john@example.com");
        Person admin = new Admin(2, "Jane Smith", "jane@example.com", "ALL");

        assertAll(
                () -> assertThat(UserService.getPersonInfo(user))
                        .isEqualTo("User: John Doe (john@example.com)"),
                () -> assertThat(UserService.getPersonInfo(admin))
                        .isEqualTo("Admin: Jane Smith (jane@example.com) with permissions: ALL")
        );
    }

    @Test
    void testUpdateEmail() {
        Person user = new User(1, "John Doe", "john@example.com");
        Person admin = new Admin(2, "Jane Smith", "jane@example.com", "ALL");

        Person updatedUser = UserService.updateEmail(user, "john.doe@example.com");
        Person updatedAdmin = UserService.updateEmail(admin, "jane.smith@example.com");

        assertAll(
                () -> assertThat(updatedUser)
                        .isInstanceOf(User.class)
                        .extracting(u -> ((User) u).email())
                        .isEqualTo("john.doe@example.com"),
                () -> assertThat(updatedAdmin)
                        .isInstanceOf(Admin.class)
                        .extracting(a -> ((Admin) a).email())
                        .isEqualTo("jane.smith@example.com")
                );
    }
}